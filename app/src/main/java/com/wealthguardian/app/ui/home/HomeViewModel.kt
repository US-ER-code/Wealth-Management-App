package com.wealthguardian.app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.wealthguardian.app.data.model.*
import com.wealthguardian.app.data.repository.ConsentRepository
import com.wealthguardian.app.data.repository.DataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val isLoading     = MutableLiveData(true)
    val netWorth      = MutableLiveData<NetWorth>()
    val timelineItems = MutableLiveData<List<TimelineItem>>()
    val insights      = MutableLiveData<List<Insight>>()
    val accounts      = MutableLiveData<Map<AccountType, Double>>()

    /** Live consent list — observed by HomeFragment for the consent card */
    val consents = ConsentRepository.consentsFlow
        .map { it } // expose as Flow; HomeFragment converts via .asLiveData()
        .asLiveData()

    init { loadData() }

    fun loadData() {
        viewModelScope.launch {
            isLoading.value = true
            delay(1000)
            recompute()
            isLoading.value = false
        }
    }

    /**
     * Recompute all consent-gated data using the current active institutions.
     * Called on first load AND whenever consent state changes.
     */
    private fun recompute() {
        val active = ConsentRepository.activeInstitutions

        // Filter each data set by whether that institution has an active consent
        val banks  = DataRepository.getBankAccounts().filter { active.containsKey(it.institutionKey) }
        val loans  = DataRepository.getLoanAccounts().filter { active.containsKey(it.institutionKey) }
        val invs   = DataRepository.getInvestments().filter  { active.containsKey(it.institutionKey) }
        val insure = DataRepository.getInsurancePolicies().filter { active.containsKey(it.institutionKey) }

        val totalAssets     = banks.sumOf { it.balance } + invs.sumOf { it.currentValue } + insure.sumOf { it.coverage }
        val totalLiabilities= loans.sumOf { it.outstanding }
        val netTotal        = totalAssets - totalLiabilities

        netWorth.value = NetWorth(
            total       = netTotal,
            cash        = banks.sumOf { it.balance },
            investments = invs.sumOf { it.currentValue },
            liabilities = -totalLiabilities,
            todayChange = if (netTotal > 0) netTotal * 0.003 else 0.0,
            lastSynced  = "Just now",
            sparklineData = listOf(20f, 22f, 21f, 23f, 22.5f, 24.5f)
        )

        accounts.value = mapOf(
            AccountType.BANK       to banks.sumOf  { it.balance },
            AccountType.LOAN       to loans.sumOf  { it.outstanding },
            AccountType.INVESTMENT to invs.sumOf   { it.currentValue },
            AccountType.INSURANCE  to insure.sumOf { it.coverage }
        )

        timelineItems.value = DataRepository.getTimelineItems()
            .filter { active.containsKey(it.institutionKey) }

        insights.value = DataRepository.getInsights()
            .filter { it.institutionKey.isBlank() || active.containsKey(it.institutionKey) }
    }

    /**
     * Triggered from ConsentFragment (or anywhere) after a consent is revoked.
     * Immediately recomputes all displayed data.
     */
    fun onConsentChanged() {
        viewModelScope.launch { recompute() }
    }

    fun snoozeInsight(id: String) {
        insights.value = insights.value?.filterNot { it.id == id }
    }

    // Extension: check active institutions set for a key
    private fun Set<String>.containsKey(key: String): Boolean =
        isEmpty() || any { it.equals(key, ignoreCase = true) || key.contains(it, ignoreCase = true) || it.contains(key, ignoreCase = true) }
}
