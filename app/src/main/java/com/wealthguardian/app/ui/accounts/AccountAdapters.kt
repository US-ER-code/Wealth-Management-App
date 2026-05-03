package com.wealthguardian.app.ui.accounts
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.wealthguardian.app.R
import com.wealthguardian.app.data.model.*
import com.wealthguardian.app.databinding.*
import com.wealthguardian.app.utils.CurrencyFormatter

class BankAccountAdapter(private val items: List<Account>) : RecyclerView.Adapter<BankAccountAdapter.VH>() {
    inner class VH(val b: ItemBankAccountBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemBankAccountBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(h: VH, i: Int) { val x = items[i]
        h.b.tvBankName.text = x.name; h.b.tvInstitution.text = x.institution
        h.b.tvBalance.text  = CurrencyFormatter.format(x.balance)
        h.b.tvSynced.text   = "Synced ${x.lastSynced}"; h.b.tvInsight.text = x.insight
        h.b.tvInitials.text = x.institution.take(2).uppercase() }
}
class LoanAccountAdapter(private val items: List<LoanAccount>) : RecyclerView.Adapter<LoanAccountAdapter.VH>() {
    inner class VH(val b: ItemLoanAccountBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemLoanAccountBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(h: VH, i: Int) { val x = items[i]; val ctx = h.b.root.context
        h.b.tvLoanName.text   = x.name; h.b.tvInstitution.text = x.institution
        h.b.tvOutstanding.text= CurrencyFormatter.format(x.outstanding)
        h.b.tvEmi.text        = "EMI: ${CurrencyFormatter.format(x.emiAmount)}"
        h.b.tvDueDate.text    = "Due: ${x.dueDate}"; h.b.tvTenure.text = x.tenure
        h.b.tvInitials.text   = x.institution.take(2).uppercase()
        h.b.tvDueBadge.visibility = if (x.isDue) View.VISIBLE else View.GONE
        h.b.cardRoot.strokeColor  = ContextCompat.getColor(ctx, if (x.isDue) R.color.error_red else R.color.card_stroke) }
}
class InvestmentAdapter(private val items: List<Investment>) : RecyclerView.Adapter<InvestmentAdapter.VH>() {
    inner class VH(val b: ItemInvestmentBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemInvestmentBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(h: VH, i: Int) { val x = items[i]; val ctx = h.b.root.context
        h.b.tvName.text         = x.name; h.b.tvType.text = x.type
        h.b.tvCurrentValue.text = CurrencyFormatter.format(x.currentValue)
        h.b.tvInvested.text     = "Invested: ${CurrencyFormatter.format(x.invested)}"
        h.b.tvReturns.text      = "${if (x.returns>=0) "+" else ""}${CurrencyFormatter.format(x.returns)} (${"%.1f".format(x.returnsPercent)}%)"
        h.b.tvReturns.setTextColor(ContextCompat.getColor(ctx, if (x.returns>=0) R.color.success_green else R.color.error_red))
        h.b.tvInitials.text = x.name.take(2).uppercase() }
}
class InsuranceAdapter(private val items: List<InsurancePolicy>) : RecyclerView.Adapter<InsuranceAdapter.VH>() {
    inner class VH(val b: ItemInsuranceBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemInsuranceBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(h: VH, i: Int) { val x = items[i]
        h.b.tvName.text     = x.name; h.b.tvProvider.text = x.provider
        h.b.tvCoverage.text = "Coverage: ${CurrencyFormatter.format(x.coverage)}"
        h.b.tvPremium.text  = "Premium: ${CurrencyFormatter.format(x.premium)}"
        h.b.tvDueDate.text  = "Due: ${x.dueDate}"; h.b.tvType.text = x.policyType
        h.b.tvInitials.text = x.provider.take(2).uppercase() }
}
