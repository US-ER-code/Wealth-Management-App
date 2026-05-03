package com.wealthguardian.app.data.repository

import com.wealthguardian.app.data.model.Consent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*

/**
 * Singleton that holds the live list of AA consents.
 * ALL ViewModels that need consent-aware data observe [consentsFlow].
 * Revoking here automatically propagates to Home, Accounts, Timeline, Insights, etc.
 */
object ConsentRepository {

    // ── Seed consents — each maps to specific institution names used in data ──
    private val seed: List<Consent> = listOf(
        Consent(
            id = "c1", institution = "HDFC Bank",
            purpose = "Aggregate and display financial data",
            scope = "Savings & Current", duration = "12 months",
            createdDate = "Nov 1, 2023", expiryDate = "Nov 1, 2024",
            isActive = true, aaHandle = "user@onemoney",
            consentHandle = "CH-HDFC0001", dataFetchStatus = "ACTIVE",
            // Which institution names in data this consent covers
            coveredInstitutions = listOf("HDFC Bank", "HDFC ERGO")
        ),
        Consent(
            id = "c2", institution = "ICICI Bank",
            purpose = "Aggregate and display financial data",
            scope = "Credit Card & Loans", duration = "12 months",
            createdDate = "Oct 15, 2023", expiryDate = "Oct 15, 2024",
            isActive = true, aaHandle = "user@finvu",
            consentHandle = "CH-ICICI0002", dataFetchStatus = "ACTIVE",
            coveredInstitutions = listOf("ICICI Bank")
        ),
        Consent(
            id = "c3", institution = "CAMS Mutual Funds",
            purpose = "Aggregate and display financial data",
            scope = "Investments", duration = "12 months",
            createdDate = "Oct 10, 2023", expiryDate = "Oct 10, 2024",
            isActive = true, aaHandle = "user@cams",
            consentHandle = "CH-CAMS0003", dataFetchStatus = "ACTIVE",
            coveredInstitutions = listOf("Mutual Fund", "CAMS")
        ),
        Consent(
            id = "c4", institution = "SBI",
            purpose = "Aggregate and display financial data",
            scope = "Savings, Loan & FD", duration = "6 months",
            createdDate = "Aug 1, 2023", expiryDate = "Feb 1, 2024",
            isActive = true, aaHandle = "user@sbi",
            consentHandle = "CH-SBI0004", dataFetchStatus = "ACTIVE",
            coveredInstitutions = listOf("State Bank of India", "SBI")
        ),
        Consent(
            id = "c5", institution = "LIC",
            purpose = "Aggregate and display financial data",
            scope = "Insurance Policies", duration = "12 months",
            createdDate = "Sep 1, 2023", expiryDate = "Sep 1, 2024",
            isActive = true, aaHandle = "user@lic",
            consentHandle = "CH-LIC0005", dataFetchStatus = "ACTIVE",
            coveredInstitutions = listOf("LIC")
        ),
        Consent(
            id = "c6", institution = "Zerodha & Star Health",
            purpose = "Aggregate and display financial data",
            scope = "Equity & Health Insurance", duration = "12 months",
            createdDate = "Sep 10, 2023", expiryDate = "Sep 10, 2024",
            isActive = true, aaHandle = "user@zerodha",
            consentHandle = "CH-ZSH0006", dataFetchStatus = "ACTIVE",
            coveredInstitutions = listOf("Equity", "Star Health Ins")
        )
    )

    private val _consentsFlow = MutableStateFlow(seed)
    val consentsFlow: StateFlow<List<Consent>> = _consentsFlow.asStateFlow()

    /** Get only currently active consents */
    val activeConsents: List<Consent>
        get() = _consentsFlow.value.filter { it.isActive }

    /** Set of institution name fragments that currently have active consent */
    val activeInstitutions: Set<String>
        get() = activeConsents.flatMap { it.coveredInstitutions }.toSet()

    fun revoke(id: String) {
        _consentsFlow.value = _consentsFlow.value.map {
            if (it.id == id) it.copy(isActive = false, dataFetchStatus = "REVOKED") else it
        }
    }

    fun addConsent(consent: Consent) {
        _consentsFlow.value = _consentsFlow.value.toMutableList().apply { add(0, consent) }
    }

    /**
     * Check if a given institution name is covered by an active consent.
     * Does a case-insensitive substring match against all coveredInstitutions.
     */
    fun isInstitutionActive(institutionName: String): Boolean {
        return activeInstitutions.any { covered ->
            institutionName.contains(covered, ignoreCase = true) ||
            covered.contains(institutionName, ignoreCase = true)
        }
    }
}
