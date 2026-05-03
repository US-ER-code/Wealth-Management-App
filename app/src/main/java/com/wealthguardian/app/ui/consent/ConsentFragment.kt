package com.wealthguardian.app.ui.consent

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels  // activityViewModels so HomeViewModel is shared
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.wealthguardian.app.R
import com.wealthguardian.app.data.api.MockApiService
import com.wealthguardian.app.data.model.Consent
import com.wealthguardian.app.data.repository.ConsentRepository
import com.wealthguardian.app.databinding.*
import com.wealthguardian.app.ui.home.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

// ── ViewModel ─────────────────────────────────────────────
class ConsentViewModel : ViewModel() {

    /** Mirror ConsentRepository as LiveData for the fragment */
    val consents: LiveData<List<Consent>> = ConsentRepository.consentsFlow.asLiveData()

    val isLoading = MutableLiveData(false)
    val statusMsg = MutableLiveData<String?>()

    fun revoke(id: String) {
        // Revoke in the shared repository — this automatically propagates to
        // HomeViewModel and AccountsViewModel via their Flow collectors
        ConsentRepository.revoke(id)
        statusMsg.value = "✅ Consent revoked. All related data has been removed from your dashboard."
    }

    fun initiateAAConsent(aaHandle: String, institution: String, scope: String, coveredInstitutions: List<String>) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                statusMsg.value = "📡 Initiating consent with AA framework…"

                val initResult = MockApiService.initiateAAConsent(
                    aaHandle = aaHandle,
                    purpose = "Account aggregation for $institution"
                )
                initResult.fold(
                    onFailure = { e ->
                        statusMsg.value = "❌ ${e.message}"
                        return@launch
                    },
                    onSuccess = { initResp ->
                        statusMsg.value = "⏳ Consent sent (${initResp.consentHandle})\nWaiting for approval in your AA app…"
                        delay(3000)
                        statusMsg.value = "✅ Approved! Fetching financial data…"

                        MockApiService.fetchFinancialData(initResp.consentHandle, aaHandle).fold(
                            onFailure = { e -> statusMsg.value = "❌ Fetch failed: ${e.message}" },
                            onSuccess = { fetchResp ->
                                val sdf = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                                val now = sdf.format(Date())
                                val cal = Calendar.getInstance().apply { add(Calendar.MONTH, 12) }
                                val expiry = sdf.format(cal.time)

                                val newConsent = Consent(
                                    id = initResp.consentHandle,
                                    institution = institution,
                                    purpose = "Aggregate and display financial data",
                                    scope = scope,
                                    duration = "12 months",
                                    createdDate = now,
                                    expiryDate = expiry,
                                    isActive = true,
                                    aaHandle = aaHandle,
                                    consentHandle = initResp.consentHandle,
                                    dataFetchStatus = "ACTIVE",
                                    coveredInstitutions = coveredInstitutions
                                )
                                // Add to shared repository — propagates everywhere
                                ConsentRepository.addConsent(newConsent)

                                val accountsSummary = fetchResp.accounts.joinToString("\n") {
                                    "  • ${it.accountType} ${it.maskedAccNumber} — ₹${"%.0f".format(it.balance)}"
                                }
                                statusMsg.value = "🎉 Linked ${fetchResp.accounts.size} account(s) from $institution:\n$accountsSummary\n\nYour dashboard is updated!"
                            }
                        )
                    }
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}

// ── Adapter ───────────────────────────────────────────────
class ConsentAdapter(
    private val onRevoke: (Consent) -> Unit,
    private val onDetail: (Consent) -> Unit
) : ListAdapter<Consent, ConsentAdapter.VH>(Diff()) {

    inner class VH(val b: ItemConsentBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemConsentBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, i: Int) {
        val x = getItem(i); val ctx = h.b.root.context
        h.b.tvInstitution.text = x.institution
        h.b.tvPurpose.text     = x.purpose
        h.b.tvScope.text       = "Scope: ${x.scope}"
        h.b.tvDuration.text    = "Duration: ${x.duration}"
        h.b.tvCreated.text     = "Created: ${x.createdDate}"
        h.b.tvExpiry.text      = "Expires: ${x.expiryDate}"
        h.b.tvInitials.text    = x.institution.take(2).uppercase()

        val statusLabel = when {
            !x.isActive          -> "Revoked"
            x.dataFetchStatus == "ACTIVE" -> "Active"
            else                 -> x.dataFetchStatus
        }
        h.b.tvStatus.text = statusLabel
        h.b.tvStatus.setTextColor(ContextCompat.getColor(ctx,
            if (x.isActive) R.color.success_green else R.color.error_red))
        h.b.btnRevoke.visibility = if (x.isActive) View.VISIBLE else View.GONE
        h.b.cardRoot.alpha       = if (x.isActive) 1f else 0.55f
        h.b.btnRevoke.setOnClickListener { onRevoke(x) }
        h.b.root.setOnClickListener { onDetail(x) }
    }

    class Diff : DiffUtil.ItemCallback<Consent>() {
        override fun areItemsTheSame(o: Consent, n: Consent) = o.id == n.id
        override fun areContentsTheSame(o: Consent, n: Consent) = o == n
    }
}

// ── Fragment ──────────────────────────────────────────────
class ConsentFragment : Fragment() {
    private var _b: FragmentConsentBinding? = null
    private val b get() = _b!!
    private val vm: ConsentViewModel by viewModels()

    // Shared HomeViewModel so we can trigger a recompute after consent change
    private val homeVm: HomeViewModel by activityViewModels()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentConsentBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(v: View, s: Bundle?) {
        super.onViewCreated(v, s)

        val adapter = ConsentAdapter(
            onRevoke = { showRevokeDialog(it) },
            onDetail = { showConsentDetail(it) }
        )
        b.rvConsents.layoutManager = LinearLayoutManager(context)
        b.rvConsents.adapter = adapter

        vm.consents.observe(viewLifecycleOwner) { cs ->
            adapter.submitList(cs)
            b.tvActiveCount.text = "${cs.count { it.isActive }} Active Consents"
            b.tvTotalCount.text  = "${cs.size} Total"
        }

        vm.statusMsg.observe(viewLifecycleOwner) { msg ->
            msg?.let { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
        }

        b.btnLinkNew.setOnClickListener { showAAConsentDialog() }
    }

    private fun showRevokeDialog(consent: Consent) {
        val coveredList = consent.coveredInstitutions.joinToString(", ")
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Revoke Consent for ${consent.institution}?")
            .setMessage(
                "This will:\n\n" +
                "• Stop fetching new data from ${consent.institution}\n" +
                "• Remove the following from your dashboard:\n  $coveredList\n\n" +
                "Accounts, loans, investments and insurance linked via this consent will no longer appear in your net worth or account summaries."
            )
            .setPositiveButton("Revoke Access") { _, _ ->
                vm.revoke(consent.id)
                // Immediately refresh Home screen data
                homeVm.onConsentChanged()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAAConsentDialog() {
        val ctx = requireContext()
        val layout = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL; setPadding(64, 32, 64, 16)
        }

        val etHandle = TextInputEditText(ctx).apply { hint = "AA Handle (e.g. 9999999999@onemoney)" }
        val etInstitution = TextInputEditText(ctx).apply { hint = "Institution name (e.g. ICICI Bank)" }
        val etCovered = TextInputEditText(ctx).apply {
            hint = "Institution keys covered (comma-separated, e.g. ICICI Bank)"
            setText("")
        }
        val tvScopeLbl = TextView(ctx).apply { text = "Data Scope"; setPadding(0, 24, 0, 8) }
        val scopes = arrayOf("Savings & Current", "Credit Card & Loans", "Investments", "Savings, Loan & FD", "Insurance Policies", "Equity & Insurance")
        val spScope = Spinner(ctx).apply { adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, scopes) }
        val tvHint = TextView(ctx).apply {
            text = "ℹ️ Demo mode: any handle with @onemoney / @finvu / @setu works. Approval is simulated automatically."
            textSize = 11f; setPadding(0, 16, 0, 0)
        }

        layout.addView(etHandle); layout.addView(etInstitution); layout.addView(etCovered)
        layout.addView(tvScopeLbl); layout.addView(spScope); layout.addView(tvHint)

        MaterialAlertDialogBuilder(ctx, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Link Account via AA")
            .setView(ScrollView(ctx).apply { addView(layout) })
            .setPositiveButton("Initiate Consent") { _, _ ->
                val handle = etHandle.text?.toString()?.trim() ?: ""
                val inst   = etInstitution.text?.toString()?.trim() ?: ""
                val coveredRaw = etCovered.text?.toString()?.trim() ?: ""
                val covered = if (coveredRaw.isBlank()) listOf(inst)
                              else coveredRaw.split(",").map { it.trim() }.filter { it.isNotBlank() }
                val scope = scopes[spScope.selectedItemPosition]

                if (handle.isBlank() || inst.isBlank()) {
                    Toast.makeText(ctx, "Enter AA handle and institution name", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                vm.initiateAAConsent(handle, inst, scope, covered)
                // HomeViewModel will auto-update via ConsentRepository flow
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showConsentDetail(consent: Consent) {
        val covered = consent.coveredInstitutions.joinToString(", ")
        val status = if (consent.isActive) "✅ Active" else "❌ Revoked"
        val dataStatus = when (consent.dataFetchStatus) {
            "ACTIVE"  -> "✅ Fetching data regularly"
            "REVOKED" -> "❌ Stopped — consent revoked"
            else      -> consent.dataFetchStatus
        }
        val msg = buildString {
            appendLine("🏦 Institution: ${consent.institution}")
            appendLine("📂 Scope: ${consent.scope}")
            appendLine("🔗 Status: $status")
            appendLine("📡 Data Fetch: $dataStatus")
            appendLine("📋 Covers: $covered")
            appendLine("⏱️ Duration: ${consent.duration}")
            appendLine("📅 Created: ${consent.createdDate}")
            appendLine("📅 Expires: ${consent.expiryDate}")
            if (consent.aaHandle.isNotBlank()) appendLine("🪪 AA Handle: ${consent.aaHandle}")
            if (consent.consentHandle.isNotBlank()) appendLine("🔑 Handle: ${consent.consentHandle}")
        }
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Consent Details")
            .setMessage(msg)
            .setPositiveButton("Close", null)
            .apply {
                if (consent.isActive) {
                    setNegativeButton("Revoke") { _, _ -> showRevokeDialog(consent) }
                }
            }
            .show()
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
