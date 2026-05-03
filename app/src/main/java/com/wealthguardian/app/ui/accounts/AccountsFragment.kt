package com.wealthguardian.app.ui.accounts
import android.os.Bundle; import android.view.*
import androidx.fragment.app.Fragment; import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.wealthguardian.app.databinding.FragmentAccountsBinding

class AccountsFragment : Fragment() {
    private var _b: FragmentAccountsBinding? = null
    private val b get() = _b!!
    private val vm: AccountsViewModel by viewModels()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentAccountsBinding.inflate(i, c, false); return b.root }
    override fun onViewCreated(v: View, s: Bundle?) {
        super.onViewCreated(v, s)
        vm.summary.observe(viewLifecycleOwner) { b.tvSummaryTitle.text=it.first; b.tvSummaryValue.text=it.second }
        vm.bankAccounts.observe(viewLifecycleOwner) {
            if (b.tabLayout.selectedTabPosition == 0) { b.rvAccounts.layoutManager=LinearLayoutManager(context); b.rvAccounts.adapter=BankAccountAdapter(it) } }
        vm.loanAccounts.observe(viewLifecycleOwner) {
            if (b.tabLayout.selectedTabPosition == 1) { b.rvAccounts.layoutManager=LinearLayoutManager(context); b.rvAccounts.adapter=LoanAccountAdapter(it) } }
        vm.investments.observe(viewLifecycleOwner) {
            if (b.tabLayout.selectedTabPosition == 2) { b.rvAccounts.layoutManager=LinearLayoutManager(context); b.rvAccounts.adapter=InvestmentAdapter(it) } }
        vm.insurance.observe(viewLifecycleOwner) {
            if (b.tabLayout.selectedTabPosition == 3) { b.rvAccounts.layoutManager=LinearLayoutManager(context); b.rvAccounts.adapter=InsuranceAdapter(it) } }
        b.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(t: TabLayout.Tab?) { when(t?.position){0->vm.loadBankAccounts();1->vm.loadLoanAccounts();2->vm.loadInvestments();3->vm.loadInsurance()} }
            override fun onTabUnselected(t: TabLayout.Tab?) {}
            override fun onTabReselected(t: TabLayout.Tab?) {}
        })
        vm.loadBankAccounts()
    }
    override fun onDestroyView() { super.onDestroyView(); _b=null }
}
