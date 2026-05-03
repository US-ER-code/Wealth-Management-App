package com.wealthguardian.app.ui.onboarding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wealthguardian.app.databinding.ItemOnboardingPageBinding

class OnboardingAdapter(private val pages: List<Triple<String, String, Int>>) :
    RecyclerView.Adapter<OnboardingAdapter.VH>() {
    inner class VH(val b: ItemOnboardingPageBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemOnboardingPageBinding.inflate(LayoutInflater.from(p.context), p, false))
    override fun getItemCount() = pages.size
    override fun onBindViewHolder(h: VH, i: Int) {
        h.b.tvTitle.text = pages[i].first
        h.b.tvDescription.text = pages[i].second
        h.b.ivIllustration.setImageResource(pages[i].third)
    }
}
