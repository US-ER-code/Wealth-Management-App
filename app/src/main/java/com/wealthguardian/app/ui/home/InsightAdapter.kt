package com.wealthguardian.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wealthguardian.app.R
import com.wealthguardian.app.data.model.Insight
import com.wealthguardian.app.data.model.InsightType
import com.wealthguardian.app.databinding.ItemInsightBinding
import com.wealthguardian.app.ui.insights.InsightDetailActivity

class InsightAdapter(private val onSnooze: (String) -> Unit) :
    ListAdapter<Insight, InsightAdapter.VH>(Diff()) {

    inner class VH(val b: ItemInsightBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemInsightBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, i: Int) {
        val x = getItem(i); val ctx = h.b.root.context
        h.b.tvTitle.text = x.title
        h.b.tvDescription.text = x.description
        h.b.tvAction.text = x.actionLabel

        val (iconRes, color) = when (x.type) {
            InsightType.ALERT   -> R.drawable.ic_alert   to R.color.error_red
            InsightType.WARNING -> R.drawable.ic_warning  to R.color.warning_amber
            InsightType.INFO    -> R.drawable.ic_info     to R.color.teal_primary
            InsightType.SUCCESS -> R.drawable.ic_check    to R.color.success_green
        }
        h.b.ivIcon.setImageResource(iconRes)
        h.b.tvAction.setTextColor(ContextCompat.getColor(ctx, color))

        // Tap insight row or action → open InsightDetailActivity
        h.b.tvAction.setOnClickListener { InsightDetailActivity.launch(ctx, x) }
        h.b.root.setOnClickListener    { InsightDetailActivity.launch(ctx, x) }

        // Dismiss/snooze button
        h.b.ivDismiss.setOnClickListener { onSnooze(x.id) }
    }

    class Diff : DiffUtil.ItemCallback<Insight>() {
        override fun areItemsTheSame(o: Insight, n: Insight) = o.id == n.id
        override fun areContentsTheSame(o: Insight, n: Insight) = o == n
    }
}
