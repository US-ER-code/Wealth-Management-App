package com.wealthguardian.app.ui.goals

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.wealthguardian.app.R
import com.wealthguardian.app.WealthGuardianApp
import com.wealthguardian.app.data.db.AppDatabase
import com.wealthguardian.app.data.model.Goal
import com.wealthguardian.app.databinding.*
import com.wealthguardian.app.utils.CurrencyFormatter
import kotlinx.coroutines.launch
import java.util.UUID

// ── ViewModel ─────────────────────────────────────────────
class GoalsViewModel(private val db: AppDatabase) : ViewModel() {
    val goals: LiveData<List<Goal>> = db.goalDao().getAllLive()

    fun addGoal(name: String, emoji: String, target: Double, category: String, sipAmount: Double, sipDay: Int) {
        viewModelScope.launch {
            val goal = Goal(
                id = UUID.randomUUID().toString(),
                name = name, emoji = emoji,
                targetAmount = target, currentAmount = 0.0,
                category = category, sipAmount = sipAmount, sipDay = sipDay
            )
            db.goalDao().insert(goal)
        }
    }

    fun addLumpsum(goal: Goal, amount: Double) {
        viewModelScope.launch { db.goalDao().addLumpsum(goal.id, amount) }
    }

    fun updateSip(goal: Goal, amount: Double, day: Int) {
        viewModelScope.launch { db.goalDao().updateSip(goal.id, amount, day) }
    }

    fun deleteGoal(goal: Goal) {
        viewModelScope.launch { db.goalDao().delete(goal) }
    }
}

class GoalsViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return GoalsViewModel(db) as T
    }
}

// ── Adapter ───────────────────────────────────────────────
class GoalsAdapter(
    private val onContribute: (Goal) -> Unit,
    private val onLongPress: (Goal) -> Unit
) : ListAdapter<Goal, GoalsAdapter.VH>(Diff()) {

    inner class VH(val b: ItemGoalBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemGoalBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, i: Int) {
        val x = getItem(i)
        h.b.tvEmoji.text         = x.emoji
        h.b.tvGoalName.text      = x.name
        h.b.tvCategory.text      = x.category
        h.b.tvCurrentAmount.text = CurrencyFormatter.format(x.currentAmount)
        h.b.tvTargetAmount.text  = "of ${CurrencyFormatter.format(x.targetAmount)}"
        h.b.tvProgress.text      = "${x.progress}% complete"
        h.b.progressBar.progress = x.progress

        // SIP badge
        val hasSip = x.sipAmount > 0
        // Try to set SIP info if the view exists in layout
        try {
            h.b.root.findViewWithTag<TextView>("tvSip")?.apply {
                visibility = if (hasSip) View.VISIBLE else View.GONE
                text = "SIP: ${CurrencyFormatter.format(x.sipAmount)}/mo"
            }
        } catch (_: Exception) {}

        h.b.btnContribute.setOnClickListener { onContribute(x) }
        h.b.root.setOnLongClickListener { onLongPress(x); true }
    }

    class Diff : DiffUtil.ItemCallback<Goal>() {
        override fun areItemsTheSame(o: Goal, n: Goal) = o.id == n.id
        override fun areContentsTheSame(o: Goal, n: Goal) = o == n
    }
}

// ── Fragment ──────────────────────────────────────────────
class GoalsFragment : Fragment() {
    private var _b: FragmentGoalsBinding? = null
    private val b get() = _b!!
    private val vm: GoalsViewModel by viewModels {
        GoalsViewModelFactory((requireActivity().application as WealthGuardianApp).database)
    }

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentGoalsBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(v: View, s: Bundle?) {
        super.onViewCreated(v, s)
        val adapter = GoalsAdapter(
            onContribute = { showContributeDialog(it) },
            onLongPress  = { showDeleteGoalDialog(it) }
        )
        b.rvGoals.layoutManager = LinearLayoutManager(context)
        b.rvGoals.adapter = adapter

        vm.goals.observe(viewLifecycleOwner) { goals ->
            adapter.submitList(goals)
            b.tvTotalGoals.text = "${goals.size} Active Goals"
            val avg = if (goals.isEmpty()) 0 else goals.sumOf { it.progress } / goals.size
            b.tvAvgProgress.text = "Avg Progress: $avg%"
        }
        b.fabAddGoal.setOnClickListener { showAddGoalDialog() }
    }

    private fun showAddGoalDialog() {
        val ctx = requireContext()
        val layout = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(64, 32, 64, 16)
        }

        val emojiOptions = arrayOf("🏖️","🚨","🎓","🏠","🚗","💍","✈️","💊","📱","💼")
        val etName     = TextInputEditText(ctx).apply { hint = "Goal name (e.g. Retirement)" }
        val tvEmojiLbl = TextView(ctx).apply { text = "Emoji"; setPadding(0,24,0,8) }
        val spEmoji    = Spinner(ctx).apply { adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, emojiOptions) }
        val etTarget   = TextInputEditText(ctx).apply { hint = "Target amount (₹)"; inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL }
        val tvCatLbl   = TextView(ctx).apply { text = "Category"; setPadding(0,24,0,8) }
        val categories = arrayOf("Retirement","Emergency","Education","Home","Vehicle","Wedding","Travel","Health","Tech","Business","Other")
        val spCat      = Spinner(ctx).apply { adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, categories) }
        val etSip      = TextInputEditText(ctx).apply { hint = "Monthly SIP amount (₹) – optional"; inputType = android.text.InputType.TYPE_CLASS_NUMBER }
        val tvSipDay   = TextView(ctx).apply { text = "SIP deduction day"; setPadding(0,24,0,8) }
        val sipDays    = (1..28).map { it.toString() }.toTypedArray()
        val spSipDay   = Spinner(ctx).apply { adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, sipDays) }

        layout.addView(etName); layout.addView(tvEmojiLbl); layout.addView(spEmoji)
        layout.addView(etTarget); layout.addView(tvCatLbl); layout.addView(spCat)
        layout.addView(etSip); layout.addView(tvSipDay); layout.addView(spSipDay)

        MaterialAlertDialogBuilder(ctx, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Add New Goal")
            .setView(layout)
            .setPositiveButton("Create Goal") { _, _ ->
                val name   = etName.text?.toString()?.trim() ?: ""
                val target = etTarget.text?.toString()?.toDoubleOrNull() ?: 0.0
                val sip    = etSip.text?.toString()?.toDoubleOrNull() ?: 0.0
                val sipDay = spSipDay.selectedItemPosition + 1
                if (name.isBlank() || target <= 0) {
                    Toast.makeText(ctx, "Please enter a valid name and target amount", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                vm.addGoal(name, emojiOptions[spEmoji.selectedItemPosition], target,
                    categories[spCat.selectedItemPosition], sip, sipDay)
                Toast.makeText(ctx, "Goal '$name' created!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showContributeDialog(goal: Goal) {
        val ctx = requireContext()
        val items = arrayOf("💸  Add Lumpsum", "📅  Update SIP Amount")
        MaterialAlertDialogBuilder(ctx, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Contribute to ${goal.name}")
            .setItems(items) { _, which ->
                when (which) {
                    0 -> showLumpsumDialog(goal)
                    1 -> showSipUpdateDialog(goal)
                }
            }.show()
    }

    private fun showLumpsumDialog(goal: Goal) {
        val ctx = requireContext()
        val layout = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL; setPadding(64, 32, 64, 16)
        }
        val tvInfo = TextView(ctx).apply {
            text = "Current: ${CurrencyFormatter.format(goal.currentAmount)}\nRemaining: ${CurrencyFormatter.format(goal.remaining)}"
            setPadding(0, 0, 0, 24)
        }
        val etAmount = TextInputEditText(ctx).apply {
            hint = "Lumpsum amount (₹)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        val etNotes = TextInputEditText(ctx).apply { hint = "Notes (optional)" }
        layout.addView(tvInfo); layout.addView(etAmount); layout.addView(etNotes)

        MaterialAlertDialogBuilder(ctx, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Add Lumpsum")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val amount = etAmount.text?.toString()?.toDoubleOrNull()
                if (amount == null || amount <= 0) {
                    Toast.makeText(ctx, "Enter a valid amount", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                vm.addLumpsum(goal, amount)
                Toast.makeText(ctx, "₹${CurrencyFormatter.format(amount)} added to ${goal.name}!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showSipUpdateDialog(goal: Goal) {
        val ctx = requireContext()
        val layout = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL; setPadding(64, 32, 64, 16)
        }
        val tvCurrent = TextView(ctx).apply {
            text = if (goal.sipAmount > 0) "Current SIP: ${CurrencyFormatter.format(goal.sipAmount)}/month on day ${goal.sipDay}"
                   else "No active SIP for this goal"
            setPadding(0, 0, 0, 24)
        }
        val etSip = TextInputEditText(ctx).apply {
            hint = "New SIP amount (₹)"; setText(if (goal.sipAmount > 0) goal.sipAmount.toLong().toString() else "")
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }
        val tvDayLbl = TextView(ctx).apply { text = "Deduction day"; setPadding(0,24,0,8) }
        val sipDays  = (1..28).map { it.toString() }.toTypedArray()
        val spDay    = Spinner(ctx).apply {
            adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, sipDays)
            setSelection((goal.sipDay - 1).coerceIn(0, 27))
        }
        layout.addView(tvCurrent); layout.addView(etSip); layout.addView(tvDayLbl); layout.addView(spDay)

        MaterialAlertDialogBuilder(ctx, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Update SIP")
            .setView(layout)
            .setPositiveButton("Update") { _, _ ->
                val amount = etSip.text?.toString()?.toDoubleOrNull() ?: 0.0
                val day = spDay.selectedItemPosition + 1
                vm.updateSip(goal, amount, day)
                val msg = if (amount > 0) "SIP updated to ${CurrencyFormatter.format(amount)}/month on day $day"
                          else "SIP cancelled for ${goal.name}"
                Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteGoalDialog(goal: Goal) {
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Delete ${goal.name}?")
            .setMessage("This will permanently remove this goal and its progress.")
            .setPositiveButton("Delete") { _, _ ->
                vm.deleteGoal(goal)
                Toast.makeText(context, "${goal.name} deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
