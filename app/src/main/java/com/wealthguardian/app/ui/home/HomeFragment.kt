package com.wealthguardian.app.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels   // shared instance across fragments
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.*
import com.wealthguardian.app.R
import com.wealthguardian.app.data.model.AccountType
import com.wealthguardian.app.data.repository.ConsentRepository
import com.wealthguardian.app.databinding.FragmentHomeBinding
import com.wealthguardian.app.utils.CurrencyFormatter
import com.wealthguardian.app.utils.DialogUtils

class HomeFragment : Fragment() {
    private var _b: FragmentHomeBinding? = null
    private val b get() = _b!!

    // activityViewModels = same instance shared with ConsentFragment
    private val vm: HomeViewModel by activityViewModels()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentHomeBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(v: View, s: Bundle?) {
        super.onViewCreated(v, s)

        val tlAdapter = TimelineAdapter()
        b.rvTimeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        b.rvTimeline.adapter = tlAdapter

        val inAdapter = InsightAdapter { vm.snoozeInsight(it) }
        b.rvInsights.layoutManager = LinearLayoutManager(context)
        b.rvInsights.adapter = inAdapter

        // ── Observe consent state for the consent card ──────
        ConsentRepository.consentsFlow.asLiveData().observe(viewLifecycleOwner) { cs ->
            val activeCount = cs.count { it.isActive }
            b.tvConsentCount.text = "$activeCount active consent${if (activeCount != 1) "s" else ""}"
            b.tvConsentNames.text = cs.filter { it.isActive }.take(3).joinToString("  ·  ") { it.institution }
        }

        // ── Observe ViewModel data ───────────────────────────
        vm.isLoading.observe(viewLifecycleOwner) {
            b.shimmerLayout.visibility  = if (it) View.VISIBLE else View.GONE
            b.contentLayout.visibility  = if (it) View.GONE   else View.VISIBLE
        }

        vm.netWorth.observe(viewLifecycleOwner) { nw ->
            b.tvNetWorthValue.text    = CurrencyFormatter.format(nw.total)
            b.tvCashValue.text        = CurrencyFormatter.format(nw.cash)
            b.tvInvestmentsValue.text = CurrencyFormatter.format(nw.investments)
            b.tvLiabilitiesValue.text = CurrencyFormatter.format(nw.liabilities)
            val changePrefix = if (nw.todayChange >= 0) "+" else ""
            b.tvTodayChange.text = "${changePrefix}${CurrencyFormatter.format(nw.todayChange)} today"
            b.tvLastSynced.text  = "Synced ${nw.lastSynced}"

            val entries = nw.sparklineData.mapIndexed { idx, fl -> Entry(idx.toFloat(), fl) }
            val ds = LineDataSet(entries, "").apply {
                color = resources.getColor(R.color.teal_primary, null); lineWidth = 2f
                setDrawCircles(false); setDrawValues(false)
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true); fillAlpha = 30
                fillColor = resources.getColor(R.color.teal_primary, null)
            }
            b.sparklineChart.apply {
                data = LineData(ds); description.isEnabled = false; legend.isEnabled = false
                setTouchEnabled(false); xAxis.isEnabled = false
                axisLeft.isEnabled = false; axisRight.isEnabled = false
                setDrawGridBackground(false); invalidate()
            }
        }

        vm.timelineItems.observe(viewLifecycleOwner) { items ->
            tlAdapter.submitList(items)
            val due = items.count { it.isDue }
            b.tvTimelineHeader.text = if (due > 0) "$due actions due this week" else "Upcoming this week"
        }

        vm.insights.observe(viewLifecycleOwner) { inAdapter.submitList(it) }

        vm.accounts.observe(viewLifecycleOwner) { acc ->
            b.tvBankBalance.text       = CurrencyFormatter.format(acc[AccountType.BANK]       ?: 0.0)
            b.tvLoanOutstanding.text   = CurrencyFormatter.format(acc[AccountType.LOAN]       ?: 0.0)
            b.tvInvestmentValue.text   = CurrencyFormatter.format(acc[AccountType.INVESTMENT] ?: 0.0)
            b.tvInsuranceCoverage.text = CurrencyFormatter.format(acc[AccountType.INSURANCE]  ?: 0.0)
        }

        // ── Navigation & actions ─────────────────────────────
        b.cardNetWorth.setOnClickListener    { findNavController().navigate(R.id.action_home_to_accounts) }
        b.btnLinkAA.setOnClickListener       { findNavController().navigate(R.id.action_home_to_consent) }
        b.btnAddFamily.setOnClickListener    { findNavController().navigate(R.id.action_home_to_family) }
        b.btnUploadVault.setOnClickListener  { DialogUtils.showVaultUploadDialog(requireContext()) { findNavController().navigate(R.id.action_home_to_vault) } }
        b.cardBank.setOnClickListener        { findNavController().navigate(R.id.action_home_to_accounts) }
        b.cardLoans.setOnClickListener       { findNavController().navigate(R.id.action_home_to_accounts) }
        b.cardInvestments.setOnClickListener { findNavController().navigate(R.id.action_home_to_accounts) }
        b.cardInsurance.setOnClickListener   { findNavController().navigate(R.id.action_home_to_accounts) }
        b.cardConsent.setOnClickListener     { findNavController().navigate(R.id.action_home_to_consent) }
        b.swipeRefresh.setOnRefreshListener  { vm.loadData(); b.swipeRefresh.isRefreshing = false }
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
