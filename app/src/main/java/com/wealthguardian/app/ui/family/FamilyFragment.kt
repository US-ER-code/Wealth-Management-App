package com.wealthguardian.app.ui.family

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.wealthguardian.app.R
import com.wealthguardian.app.WealthGuardianApp
import com.wealthguardian.app.data.db.AppDatabase
import com.wealthguardian.app.data.model.FamilyMember
import com.wealthguardian.app.data.model.FamilyRole
import com.wealthguardian.app.databinding.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

// ── Section definitions ───────────────────────────────────
data class AccessSection(val key: String, val label: String, val icon: String, val description: String)

val ALL_SECTIONS = listOf(
    AccessSection("NET_WORTH",  "Net Worth",     "💰", "View total net worth dashboard"),
    AccessSection("ACCOUNTS",   "Accounts",      "🏦", "View bank, loan & investment accounts"),
    AccessSection("VAULT",      "Document Vault","📁", "View and download stored documents"),
    AccessSection("GOALS",      "Goals",         "🎯", "View financial goals and progress"),
    AccessSection("CONSENT",    "AA Consents",   "🔗", "View linked account aggregator consents"),
    AccessSection("FAMILY",     "Family Members","👨‍👩‍👧", "View and manage family members (Admin only)"),
    AccessSection("INSIGHTS",   "Insights",      "💡", "View financial alerts and recommendations")
)

val ROLE_DEFAULT_SECTIONS = mapOf(
    FamilyRole.VIEWER    to setOf("NET_WORTH", "ACCOUNTS"),
    FamilyRole.EMERGENCY to setOf("NET_WORTH", "ACCOUNTS", "VAULT", "GOALS", "INSIGHTS"),
    FamilyRole.ADMIN     to ALL_SECTIONS.map { it.key }.toSet()
)

// ── ViewModel ─────────────────────────────────────────────
class FamilyViewModel(private val db: AppDatabase) : ViewModel() {
    val members: LiveData<List<FamilyMember>> = db.familyDao().getAllLive()

    fun addMember(name: String, email: String, phone: String, role: FamilyRole, sections: Set<String>) {
        viewModelScope.launch {
            val initials = name.split(" ").take(2).joinToString("") { it.take(1).uppercase() }
            val sdf = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
            db.familyDao().insert(FamilyMember(
                id = UUID.randomUUID().toString(),
                name = name, email = email, phone = phone,
                role = role, avatarInitials = initials,
                addedDate = "Added ${sdf.format(Date())}",
                allowedSections = sections.joinToString(","),
                inviteAccepted = false,
                inviteSentAt = System.currentTimeMillis()
            ))
        }
    }

    fun updateRole(member: FamilyMember, role: FamilyRole, sections: Set<String>) {
        viewModelScope.launch {
            db.familyDao().updateRole(member.id, role, sections.joinToString(","))
        }
    }

    fun removeMember(member: FamilyMember) {
        viewModelScope.launch { db.familyDao().deleteById(member.id) }
    }
}

class FamilyViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return FamilyViewModel(db) as T
    }
}

// ── Adapter ───────────────────────────────────────────────
class FamilyAdapter(
    private val onEdit: (FamilyMember) -> Unit,
    private val onRemove: (FamilyMember) -> Unit
) : ListAdapter<FamilyMember, FamilyAdapter.VH>(Diff()) {

    inner class VH(val b: ItemFamilyMemberBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemFamilyMemberBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, i: Int) {
        val x = getItem(i); val ctx = h.b.root.context
        h.b.tvInitials.text  = x.avatarInitials
        h.b.tvName.text      = x.name
        h.b.tvEmail.text     = x.email
        h.b.tvAddedDate.text = x.addedDate

        val (label, color) = when (x.role) {
            FamilyRole.VIEWER    -> "Viewer"    to R.color.teal_primary
            FamilyRole.EMERGENCY -> "Emergency" to R.color.warning_amber
            FamilyRole.ADMIN     -> "Admin"     to R.color.error_red
            else                 -> "Viewer"    to R.color.teal_primary
        }
        h.b.tvRole.text = label
        h.b.tvRole.setTextColor(ContextCompat.getColor(ctx, color))

        val sections = x.allowedSections.split(",").filter { it.isNotBlank() }
        val sectionLabels = ALL_SECTIONS.filter { it.key in sections }.joinToString(" · ") { it.icon + it.label }
        h.b.tvPermissions.text = if (sectionLabels.isNotBlank()) sectionLabels
                                 else "No sections assigned"

        // Invite status
        val statusText = if (x.inviteAccepted) "✓ Active" else "⏳ Invite pending"
        try { h.b.root.findViewWithTag<TextView>("tvInviteStatus")?.text = statusText } catch (_: Exception) {}

        h.b.btnRemove.setOnClickListener { onRemove(x) }
        h.b.root.setOnClickListener { onEdit(x) }
    }

    class Diff : DiffUtil.ItemCallback<FamilyMember>() {
        override fun areItemsTheSame(o: FamilyMember, n: FamilyMember) = o.id == n.id
        override fun areContentsTheSame(o: FamilyMember, n: FamilyMember) = o == n
    }
}

// ── Fragment ──────────────────────────────────────────────
class FamilyFragment : Fragment() {
    private var _b: FragmentFamilyBinding? = null
    private val b get() = _b!!
    private val vm: FamilyViewModel by viewModels {
        FamilyViewModelFactory((requireActivity().application as WealthGuardianApp).database)
    }

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentFamilyBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(v: View, s: Bundle?) {
        super.onViewCreated(v, s)
        val adapter = FamilyAdapter(
            onEdit   = { showEditMemberDialog(it) },
            onRemove = { showRemoveConfirm(it) }
        )
        b.rvFamily.layoutManager = LinearLayoutManager(context)
        b.rvFamily.adapter = adapter

        vm.members.observe(viewLifecycleOwner) { members ->
            adapter.submitList(members)
            b.tvMemberCount.text = "${members.size} Family Members"
        }
        b.btnAddMember.setOnClickListener { showAddMemberDialog() }
    }

    // ── Build the access-role form ─────────────────────────
    private fun buildMemberForm(
        existingMember: FamilyMember? = null
    ): Triple<LinearLayout, () -> Triple<String, FamilyRole, Set<String>>, () -> String> {

        val ctx = requireContext()
        val layout = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(64, 32, 64, 16)
        }

        // Name
        val etName = TextInputEditText(ctx).apply {
            hint = "Full name"
            setText(existingMember?.name ?: "")
        }
        // Email
        val etEmail = TextInputEditText(ctx).apply {
            hint = "Email address"
            inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            setText(existingMember?.email ?: "")
        }
        // Phone
        val etPhone = TextInputEditText(ctx).apply {
            hint = "Mobile number (for invite SMS)"
            inputType = android.text.InputType.TYPE_CLASS_PHONE
            setText(existingMember?.phone ?: "")
        }

        // Role picker
        val tvRoleLbl = TextView(ctx).apply { text = "Access Role"; setPadding(0, 24, 0, 8) }
        val roles = arrayOf("Viewer  — Read-only net worth & accounts",
                            "Emergency  — Critical docs + goals access",
                            "Admin  — Full visibility + invite others")
        val spRole = Spinner(ctx).apply {
            adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, roles)
            val currentIdx = when (existingMember?.role) {
                FamilyRole.EMERGENCY -> 1; FamilyRole.ADMIN -> 2; else -> 0
            }
            setSelection(currentIdx)
        }

        // Section checkboxes
        val tvSectionLbl = TextView(ctx).apply {
            text = "Custom section access  (auto-filled by role, override if needed)"
            setPadding(0, 28, 0, 8)
        }
        val existingSections = existingMember?.allowedSections?.split(",")?.toSet() ?: emptySet()
        val checkBoxes = ALL_SECTIONS.map { section ->
            CheckBox(ctx).apply {
                text = "${section.icon} ${section.label}  —  ${section.description}"
                tag = section.key
                isChecked = section.key in existingSections ||
                    (existingMember == null && section.key in (ROLE_DEFAULT_SECTIONS[FamilyRole.VIEWER] ?: emptySet()))
            }
        }

        // Auto-fill checkboxes when role changes
        spRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p: AdapterView<*>?) {}
            override fun onItemSelected(p: AdapterView<*>?, v: View?, pos: Int, id: Long) {
                val role = when (pos) { 1 -> FamilyRole.EMERGENCY; 2 -> FamilyRole.ADMIN; else -> FamilyRole.VIEWER }
                val defaults = ROLE_DEFAULT_SECTIONS[role] ?: emptySet()
                checkBoxes.forEach { cb -> cb.isChecked = cb.tag as String in defaults }
            }
        }

        layout.addView(etName); layout.addView(etEmail); layout.addView(etPhone)
        layout.addView(tvRoleLbl); layout.addView(spRole)
        layout.addView(tvSectionLbl)
        checkBoxes.forEach { layout.addView(it) }

        val getData: () -> Triple<String, FamilyRole, Set<String>> = {
            val role = when (spRole.selectedItemPosition) {
                1 -> FamilyRole.EMERGENCY; 2 -> FamilyRole.ADMIN; else -> FamilyRole.VIEWER
            }
            val sections = checkBoxes.filter { it.isChecked }.map { it.tag as String }.toSet()
            Triple(etName.text?.toString()?.trim() ?: "", role, sections)
        }
        val getEmail: () -> String = { etEmail.text?.toString()?.trim() ?: "" }

        return Triple(layout, getData, getEmail)
    }

    private fun showAddMemberDialog() {
        val ctx = requireContext()
        val (layout, getData, getEmail) = buildMemberForm()

        // Wrap in ScrollView
        val scroll = ScrollView(ctx).apply { addView(layout) }

        MaterialAlertDialogBuilder(ctx, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Add Family Member")
            .setView(scroll)
            .setPositiveButton("Send Invite") { _, _ ->
                val (name, role, sections) = getData()
                val email = getEmail()
                if (name.isBlank() || email.isBlank()) {
                    Toast.makeText(ctx, "Name and email are required", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                if (sections.isEmpty()) {
                    Toast.makeText(ctx, "Select at least one section to share", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                vm.addMember(name, email, "", role, sections)
                val roleLabel = when (role) { FamilyRole.ADMIN -> "Admin"; FamilyRole.EMERGENCY -> "Emergency"; else -> "Viewer" }
                Toast.makeText(ctx, "✉️ Invite sent to $email as $roleLabel with ${sections.size} section(s) access", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditMemberDialog(member: FamilyMember) {
        val ctx = requireContext()
        val (layout, getData, _) = buildMemberForm(existingMember = member)
        val scroll = ScrollView(ctx).apply { addView(layout) }

        MaterialAlertDialogBuilder(ctx, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Edit ${member.name}")
            .setView(scroll)
            .setPositiveButton("Save Changes") { _, _ ->
                val (_, role, sections) = getData()
                if (sections.isEmpty()) {
                    Toast.makeText(ctx, "Select at least one section", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                vm.updateRole(member, role, sections)
                Toast.makeText(ctx, "Access updated for ${member.name}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showRemoveConfirm(member: FamilyMember) {
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Remove ${member.name}?")
            .setMessage("This will revoke all access for ${member.name}. They will no longer see any of your financial data.")
            .setPositiveButton("Remove") { _, _ ->
                vm.removeMember(member)
                Toast.makeText(context, "${member.name} removed", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
