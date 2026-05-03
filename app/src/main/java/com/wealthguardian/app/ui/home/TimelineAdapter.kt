package com.wealthguardian.app.ui.home
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.wealthguardian.app.R
import com.wealthguardian.app.data.model.*
import com.wealthguardian.app.databinding.ItemTimelineBinding
import com.wealthguardian.app.utils.CurrencyFormatter

class TimelineAdapter : ListAdapter<TimelineItem, TimelineAdapter.VH>(Diff()) {
    inner class VH(val b: ItemTimelineBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemTimelineBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun onBindViewHolder(h: VH, i: Int) {
        val item = getItem(i); val ctx = h.b.root.context
        h.b.tvTitle.text    = item.title
        h.b.tvSubtitle.text = item.subtitle
        h.b.tvAmount.text   = CurrencyFormatter.format(item.amount)
        h.b.tvDueDate.text  = "Due ${item.dueDate}"
        h.b.tvDueLabel.visibility = if (item.isDue) View.VISIBLE else View.GONE
        h.b.cardRoot.strokeColor  = ContextCompat.getColor(ctx,
            if (item.isDue) R.color.error_red else R.color.card_stroke)
        h.b.ivTypeIcon.setImageResource(when (item.type) {
            TimelineType.EMI      -> R.drawable.ic_loan
            TimelineType.SIP      -> R.drawable.ic_investment
            TimelineType.PREMIUM  -> R.drawable.ic_insurance
            TimelineType.MATURITY -> R.drawable.ic_investment
        })
    }
    class Diff : DiffUtil.ItemCallback<TimelineItem>() {
        override fun areItemsTheSame(o: TimelineItem, n: TimelineItem) = o.id == n.id
        override fun areContentsTheSame(o: TimelineItem, n: TimelineItem) = o == n
    }
}
