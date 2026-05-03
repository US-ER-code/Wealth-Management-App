package com.wealthguardian.app.ui.vault

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.wealthguardian.app.R
import com.wealthguardian.app.WealthGuardianApp
import com.wealthguardian.app.data.api.*
import com.wealthguardian.app.data.db.AppDatabase
import com.wealthguardian.app.data.model.*
import com.wealthguardian.app.databinding.*
import com.wealthguardian.app.utils.CurrencyFormatter
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

// ── ViewModel ─────────────────────────────────────────────
class VaultViewModel(private val db: AppDatabase) : ViewModel() {

    val docs: LiveData<List<VaultDocument>> = db.vaultDao().getAllLive()
    val isUploading = MutableLiveData(false)
    val uploadProgress = MutableLiveData(0)
    val statusMessage = MutableLiveData<String?>()

    // Digilocker state
    val digilockerToken = MutableLiveData<String?>()
    val digilockerDocs = MutableLiveData<List<DigilockerDocument>>()

    // AA state (for Consent screen – shared here for demo)
    val aaConsentHandle = MutableLiveData<String?>()

    fun filter(cat: DocumentCategory?): LiveData<List<VaultDocument>> =
        if (cat == null) docs
        else db.vaultDao().getByCategory(cat)

    /** Upload physical document from device file picker */
    fun uploadPhysicalDocument(
        context: android.content.Context,
        uri: Uri,
        docName: String,
        category: DocumentCategory
    ) {
        viewModelScope.launch {
            try {
                isUploading.value = true
                uploadProgress.value = 10
                statusMessage.value = "Reading file…"

                val bytes = context.contentResolver.openInputStream(uri)?.readBytes() ?: run {
                    statusMessage.value = "Could not read file"
                    isUploading.value = false
                    return@launch
                }
                val mimeType = context.contentResolver.getType(uri) ?: "application/pdf"
                val fileSizeKb = bytes.size / 1024
                val fileSizeStr = if (fileSizeKb >= 1024) "${fileSizeKb/1024} MB" else "$fileSizeKb KB"

                uploadProgress.value = 30
                statusMessage.value = "Uploading to secure vault…"

                val result = MockApiService.uploadDocumentToVault(docName, category.name, bytes, mimeType)
                uploadProgress.value = 80

                result.fold(
                    onSuccess = { resp ->
                        val sdf = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                        val doc = VaultDocument(
                            id = resp.docId,
                            name = docName,
                            category = category,
                            issuer = "Uploaded by user",
                            dateAdded = sdf.format(Date()),
                            isVerified = resp.isVerified,
                            fileSize = fileSizeStr,
                            localPath = uri.toString(),
                            mimeType = mimeType,
                            uploadStatus = UploadStatus.CLOUD,
                            uploadedAt = System.currentTimeMillis()
                        )
                        db.vaultDao().insert(doc)
                        uploadProgress.value = 100
                        statusMessage.value = if (resp.isVerified) "✓ Uploaded & verified!" else "✓ Uploaded! Verification pending."
                    },
                    onFailure = { e ->
                        statusMessage.value = "Upload failed: ${e.message}"
                    }
                )
            } finally {
                isUploading.value = false
            }
        }
    }

    /** Authenticate with DigiLocker */
    fun authenticateDigilocker(mobile: String, otp: String) {
        viewModelScope.launch {
            isUploading.value = true
            statusMessage.value = "Authenticating with DigiLocker…"
            val result = MockApiService.authenticateDigilocker(mobile, otp)
            result.fold(
                onSuccess = { resp ->
                    digilockerToken.value = resp.accessToken
                    statusMessage.value = "DigiLocker connected! Welcome, ${resp.name}"
                    fetchDigilockerList(resp.accessToken)
                },
                onFailure = { e -> statusMessage.value = "DigiLocker error: ${e.message}" }
            )
            isUploading.value = false
        }
    }

    private suspend fun fetchDigilockerList(token: String) {
        statusMessage.value = "Fetching your documents…"
        val result = MockApiService.listDigilockerDocuments(token)
        result.fold(
            onSuccess = { resp -> digilockerDocs.value = resp.documents },
            onFailure = { e -> statusMessage.value = "Error: ${e.message}" }
        )
    }

    /** Pull a specific DigiLocker document and save to vault */
    fun importDigilockerDoc(doc: DigilockerDocument) {
        val token = digilockerToken.value ?: return
        viewModelScope.launch {
            isUploading.value = true
            statusMessage.value = "Downloading ${doc.docName}…"
            val pullResult = MockApiService.pullDigilockerDocument(token, doc.uri)
            pullResult.fold(
                onSuccess = { pulled ->
                    statusMessage.value = "Uploading to secure vault…"
                    val uploadResult = MockApiService.uploadDigilockerDocToVault(
                        doc.docName, mapDocType(doc.docType).name, doc.uri, pulled.base64Data)
                    uploadResult.fold(
                        onSuccess = { resp ->
                            val sdf = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                            val vaultDoc = VaultDocument(
                                id = resp.docId,
                                name = doc.docName,
                                category = mapDocType(doc.docType),
                                issuer = doc.issuedBy,
                                dateAdded = sdf.format(Date()),
                                isVerified = true,
                                fileSize = doc.fileSize,
                                digilockerUri = doc.uri,
                                digilockerDocType = doc.docType,
                                uploadStatus = UploadStatus.DIGILOCKER,
                                uploadedAt = System.currentTimeMillis()
                            )
                            db.vaultDao().insert(vaultDoc)
                            statusMessage.value = "✓ ${doc.docName} imported from DigiLocker!"
                        },
                        onFailure = { e -> statusMessage.value = "Upload failed: ${e.message}" }
                    )
                },
                onFailure = { e -> statusMessage.value = "Download failed: ${e.message}" }
            )
            isUploading.value = false
        }
    }

    fun deleteDocument(doc: VaultDocument) {
        viewModelScope.launch { db.vaultDao().delete(doc) }
    }

    private fun mapDocType(type: String) = when (type) {
        "ADHAR", "PAN", "DL", "VOTERID" -> DocumentCategory.IDENTITY
        "ITR" -> DocumentCategory.TAX
        "INSURANCE" -> DocumentCategory.INSURANCE
        else -> DocumentCategory.MISC
    }
}

class VaultViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return VaultViewModel(db) as T
    }
}

// ── Adapter ───────────────────────────────────────────────
class VaultAdapter(
    private val onClick: (VaultDocument) -> Unit,
    private val onDelete: (VaultDocument) -> Unit
) : ListAdapter<VaultDocument, VaultAdapter.VH>(Diff()) {

    inner class VH(val b: ItemVaultDocumentBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemVaultDocumentBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, i: Int) {
        val x = getItem(i); val ctx = h.b.root.context
        h.b.tvDocName.text   = x.name
        h.b.tvIssuer.text    = x.issuer
        h.b.tvDateAdded.text = x.dateAdded
        h.b.tvFileSize.text  = x.fileSize
        h.b.tvCategory.text  = x.category.name

        // Source badge
        val sourceBadge = when (x.uploadStatus) {
            UploadStatus.DIGILOCKER -> "DigiLocker ✓"
            UploadStatus.CLOUD      -> "Uploaded ✓"
            UploadStatus.UPLOADING  -> "Uploading…"
            UploadStatus.FAILED     -> "Failed ✗"
            UploadStatus.LOCAL      -> "Local"
        }
        h.b.tvVerified.text = if (x.isVerified) "Verified • $sourceBadge" else sourceBadge
        h.b.tvVerified.setTextColor(ContextCompat.getColor(ctx,
            if (x.isVerified) R.color.success_green else R.color.text_secondary))
        h.b.ivVerified.visibility = if (x.isVerified) View.VISIBLE else View.GONE

        h.b.ivDocIcon.setImageResource(when (x.category) {
            DocumentCategory.INSURANCE  -> R.drawable.ic_insurance
            DocumentCategory.LOAN       -> R.drawable.ic_loan
            DocumentCategory.INVESTMENT -> R.drawable.ic_investment
            DocumentCategory.IDENTITY   -> R.drawable.ic_identity
            DocumentCategory.TAX        -> R.drawable.ic_tax
            DocumentCategory.MISC       -> R.drawable.ic_document
        })
        h.b.root.setOnClickListener { onClick(x) }
        h.b.root.setOnLongClickListener { onDelete(x); true }
    }

    class Diff : DiffUtil.ItemCallback<VaultDocument>() {
        override fun areItemsTheSame(o: VaultDocument, n: VaultDocument) = o.id == n.id
        override fun areContentsTheSame(o: VaultDocument, n: VaultDocument) = o == n
    }
}

// ── Fragment ──────────────────────────────────────────────
class VaultFragment : Fragment() {
    private var _b: FragmentVaultBinding? = null
    private val b get() = _b!!
    private val vm: VaultViewModel by viewModels {
        VaultViewModelFactory((requireActivity().application as WealthGuardianApp).database)
    }

    private var pendingDocName = ""
    private var pendingDocCategory = DocumentCategory.MISC

    private val filePicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data ?: return@registerForActivityResult
            // Get filename from content resolver
            val fileName = requireContext().contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst(); cursor.getString(idx)
            } ?: "Document"
            showNameDocumentDialog(uri, fileName)
        }
    }

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentVaultBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(v: View, s: Bundle?) {
        super.onViewCreated(v, s)

        val adapter = VaultAdapter(
            onClick = { showDocumentDetail(it) },
            onDelete = { showDeleteConfirm(it) }
        )
        b.rvDocuments.layoutManager = GridLayoutManager(context, 2)
        b.rvDocuments.adapter = adapter

        vm.docs.observe(viewLifecycleOwner) { docs ->
            adapter.submitList(docs)
            b.tvDocumentCount.text = "${docs.size} Documents"
            b.tvVerifiedCount.text = "${docs.count { it.isVerified }} Verified"
        }

        vm.statusMessage.observe(viewLifecycleOwner) { msg ->
            msg?.let { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
        }

        // Chip filters
        b.chipAll.setOnClickListener        { vm.docs.observe(viewLifecycleOwner) { adapter.submitList(it) } }
        b.chipInsurance.setOnClickListener  { vm.filter(DocumentCategory.INSURANCE).observe(viewLifecycleOwner) { adapter.submitList(it) } }
        b.chipLoan.setOnClickListener       { vm.filter(DocumentCategory.LOAN).observe(viewLifecycleOwner) { adapter.submitList(it) } }
        b.chipInvestment.setOnClickListener { vm.filter(DocumentCategory.INVESTMENT).observe(viewLifecycleOwner) { adapter.submitList(it) } }
        b.chipIdentity.setOnClickListener   { vm.filter(DocumentCategory.IDENTITY).observe(viewLifecycleOwner) { adapter.submitList(it) } }
        b.chipTax.setOnClickListener        { vm.filter(DocumentCategory.TAX).observe(viewLifecycleOwner) { adapter.submitList(it) } }

        b.fabUpload.setOnClickListener { showUploadChoiceDialog() }
    }

    private fun showUploadChoiceDialog() {
        val items = arrayOf("📁  Upload from Device", "🏛️  Import from DigiLocker")
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Add Document")
            .setItems(items) { _, which ->
                when (which) {
                    0 -> pickFileFromDevice()
                    1 -> showDigilockerDialog()
                }
            }.show()
    }

    private fun pickFileFromDevice() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/pdf","image/jpeg","image/png"))
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        filePicker.launch(intent)
    }

    private fun showNameDocumentDialog(uri: Uri, suggestedName: String) {
        val dv = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, null)
        // Build a simple form inline
        val layout = android.widget.LinearLayout(requireContext()).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            val pad = 64
            setPadding(pad, pad/2, pad, pad/2)
        }
        val etName = TextInputEditText(requireContext()).apply {
            hint = "Document name"
            setText(suggestedName.substringBeforeLast("."))
        }
        val tvCatLabel = TextView(requireContext()).apply {
            text = "Category"; setPadding(0, 32, 0, 8)
        }
        val categories = DocumentCategory.values().map { it.name }.toTypedArray()
        val spinner = Spinner(requireContext()).apply {
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)
        }
        layout.addView(etName); layout.addView(tvCatLabel); layout.addView(spinner)

        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Document Details")
            .setView(layout)
            .setPositiveButton("Upload") { _, _ ->
                val name = etName.text?.toString()?.trim().takeIf { !it.isNullOrBlank() } ?: suggestedName
                val cat = DocumentCategory.values()[spinner.selectedItemPosition]
                vm.uploadPhysicalDocument(requireContext(), uri, name, cat)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDigilockerDialog() {
        val token = vm.digilockerToken.value
        if (token != null) {
            // Already authenticated – show document list
            showDigilockerDocList()
        } else {
            // Show auth dialog
            showDigilockerAuthDialog()
        }
    }

    private fun showDigilockerAuthDialog() {
        val layout = android.widget.LinearLayout(requireContext()).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(64, 32, 64, 32)
        }
        val etMobile = TextInputEditText(requireContext()).apply { hint = "Mobile number (linked with DigiLocker)"; inputType = android.text.InputType.TYPE_CLASS_PHONE }
        val etOtp = TextInputEditText(requireContext()).apply { hint = "OTP (use 123456 for demo)"; inputType = android.text.InputType.TYPE_CLASS_NUMBER }
        val tvHint = TextView(requireContext()).apply {
            text = "ℹ️ This is a sandbox demo. Enter any 10-digit mobile and OTP 123456."
            textSize = 12f; setPadding(0, 16, 0, 0)
        }
        layout.addView(etMobile); layout.addView(etOtp); layout.addView(tvHint)

        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Connect DigiLocker")
            .setView(layout)
            .setPositiveButton("Verify OTP") { _, _ ->
                val mobile = etMobile.text?.toString() ?: ""
                val otp = etOtp.text?.toString() ?: ""
                vm.authenticateDigilocker(mobile, otp)
                // Observe and show doc list after auth
                vm.digilockerDocs.observe(viewLifecycleOwner) { docs ->
                    if (docs.isNotEmpty()) showDigilockerDocList()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDigilockerDocList() {
        val docs = vm.digilockerDocs.value ?: return
        val items = docs.map { "📄 ${it.docName}  (${it.fileSize})" }.toTypedArray()
        val checked = BooleanArray(docs.size)

        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Your DigiLocker Documents")
            .setMultiChoiceItems(items, checked) { _, which, isChecked -> checked[which] = isChecked }
            .setPositiveButton("Import Selected") { _, _ ->
                val selected = docs.filterIndexed { i, _ -> checked[i] }
                selected.forEach { vm.importDigilockerDoc(it) }
                if (selected.isNotEmpty()) Toast.makeText(context, "Importing ${selected.size} document(s)…", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDocumentDetail(doc: VaultDocument) {
        val source = when (doc.uploadStatus) {
            UploadStatus.DIGILOCKER -> "DigiLocker (Government verified)"
            UploadStatus.CLOUD      -> "Uploaded by you"
            else                    -> "Local"
        }
        val msg = buildString {
            appendLine("📂 Category: ${doc.category.name}")
            appendLine("🏛️ Issuer: ${doc.issuer}")
            appendLine("📅 Added: ${doc.dateAdded}")
            appendLine("💾 Size: ${doc.fileSize}")
            appendLine("🔗 Source: $source")
            if (doc.digilockerDocType.isNotBlank()) appendLine("🪪 Doc Type: ${doc.digilockerDocType}")
            appendLine("✅ Verified: ${if (doc.isVerified) "Yes" else "Pending"}")
        }
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle(doc.name)
            .setMessage(msg)
            .setPositiveButton("Close", null)
            .setNegativeButton("Delete") { _, _ -> showDeleteConfirm(doc) }
            .show()
    }

    private fun showDeleteConfirm(doc: VaultDocument) {
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Delete ${doc.name}?")
            .setMessage("This will permanently remove this document from your vault.")
            .setPositiveButton("Delete") { _, _ -> vm.deleteDocument(doc) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
