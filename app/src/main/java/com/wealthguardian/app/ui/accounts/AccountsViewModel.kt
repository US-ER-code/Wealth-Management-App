package com.wealthguardian.app.ui.accounts

import androidx.lifecycle.*
import com.wealthguardian.app.data.model.*
import com.wealthguardian.app.data.repository.ConsentRepository
import com.wealthguardian.app.data.repository.DataRepository
import com.wealthguardian.app.utils.CurrencyFormatter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AccountsViewModel : ViewModel() {

    private val _bank    = MutableLiveData<List<Account>>()
    val bankAccounts: LiveData<List<Account>> = _bank

    private val _loans   = MutableLiveData<List<LoanAccount>>()
    val loanAccounts: LiveData<List<LoanAccount>> = _loans

    private val _invest  = MutableLiveData<List<Investment>>()
    val investments: LiveData<List<Investment>> = _invest

    private val _insure  = MutableLiveData<List<InsurancePolicy>>()
    val insurance: LiveData<List<InsurancePolicy>> = _insure

    private val _summary = MutableLiveData<Pair<String, String>>()
    val summary: LiveData<Pair<String, String>> = _summary

    // Track which tab is active so we can refresh it when consents change
    private var currentTab = 0

    init {
        // Re-filter displayed data whenever consent state changes (revoke/add)
        ConsentRepository.consentsFlow.onEach {
            refreshCurrentTab()
        }.launchIn(viewModelScope)
    }

    private fun refreshCurrentTab() {
        when (currentTab) {
            0 -> loadBankAccounts()
            1 -> loadLoanAccounts()
            2 -> loadInvestments()
            3 -> loadInsurance()
        }
    }

    fun loadBankAccounts() {
        currentTab = 0
        viewModelScope.launch {
            val filtered = DataRepository.getBankAccounts().filter { isActive(it.institutionKey) }
            _bank.value = filtered
            _summary.value = "Total Bank Balance" to CurrencyFormatter.format(filtered.sumOf { it.balance })
        }
    }

    fun loadLoanAccounts() {
        currentTab = 1
        viewModelScope.launch {
            val filtered = DataRepository.getLoanAccounts().filter { isActive(it.institutionKey) }
            _loans.value = filtered
            _summary.value = "Total Outstanding" to CurrencyFormatter.format(filtered.sumOf { it.outstanding })
        }
    }

    fun loadInvestments() {
        currentTab = 2
        viewModelScope.launch {
            val filtered = DataRepository.getInvestments().filter { isActive(it.institutionKey) }
            _invest.value = filtered
            _summary.value = "Portfolio Value" to CurrencyFormatter.format(filtered.sumOf { it.currentValue })
        }
    }

    fun loadInsurance() {
        currentTab = 3
        viewModelScope.launch {
            val filtered = DataRepository.getInsurancePolicies().filter { isActive(it.institutionKey) }
            _insure.value = filtered
            _summary.value = "Total Coverage" to CurrencyFormatter.format(filtered.sumOf { it.coverage })
        }
    }

    private fun isActive(institutionKey: String): Boolean =
        ConsentRepository.isInstitutionActive(institutionKey)
}
