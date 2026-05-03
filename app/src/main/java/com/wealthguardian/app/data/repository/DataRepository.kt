package com.wealthguardian.app.data.repository

import com.wealthguardian.app.data.model.*

/**
 * Static data source. Every record now carries an [institutionKey] that matches
 * the institution names declared in [ConsentRepository].coveredInstitutions.
 * Filtering by consent happens in ViewModels via [ConsentRepository.isInstitutionActive].
 */
object DataRepository {

    // ── Bank accounts ─────────────────────────────────────
    fun getBankAccounts() = listOf(
        Account("b1", "HDFC Savings",         "HDFC Bank",           AccountType.BANK, 185000.0, "2h ago",  "****4821", "Primary salary account", institutionKey = "HDFC Bank"),
        Account("b2", "SBI Current",          "State Bank of India", AccountType.BANK, 135000.0, "3h ago",  "****7732", "Business account",       institutionKey = "State Bank of India")
    )

    // ── Loan accounts ─────────────────────────────────────
    fun getLoanAccounts() = listOf(
        LoanAccount("l1", "HDFC Home Loan",    "HDFC Bank",  2450000.0, 32500.0, "Thu, Nov 14", "180 months", true,  institutionKey = "HDFC Bank"),
        LoanAccount("l2", "ICICI Car Loan",    "ICICI Bank",  380000.0, 12000.0, "Sat, Nov 16", "36 months",  false, institutionKey = "ICICI Bank"),
        LoanAccount("l3", "SBI Personal Loan", "SBI",         150000.0,  8450.0, "Mon, Nov 18", "24 months",  false, institutionKey = "SBI")
    )

    // ── Investments ───────────────────────────────────────
    fun getInvestments() = listOf(
        Investment("i1", "CAMS Mutual Funds",  "Mutual Fund", 980000.0, 800000.0, 180000.0, 22.5, institutionKey = "Mutual Fund"),
        Investment("i2", "Zerodha Stocks",     "Equity",      540000.0, 450000.0,  90000.0, 20.0, institutionKey = "Equity"),
        Investment("i3", "SBI Fixed Deposit",  "FD",          280000.0, 250000.0,  30000.0, 12.0, institutionKey = "SBI"),
        Investment("i4", "PPF Account",        "PPF",         180000.0, 150000.0,  30000.0, 20.0, institutionKey = "SBI")
    )

    // ── Insurance ─────────────────────────────────────────
    fun getInsurancePolicies() = listOf(
        InsurancePolicy("ins1", "LIC Term Insurance", "LIC",           15000000.0, 9450.0,  "Fri, Nov 15", "Term Life", institutionKey = "LIC"),
        InsurancePolicy("ins2", "Star Health",        "Star Health Ins",  500000.0, 12000.0, "Dec 1",       "Health",    institutionKey = "Star Health Ins"),
        InsurancePolicy("ins3", "HDFC ERGO Car",      "HDFC ERGO",        750000.0,  8500.0, "Jan 15",      "Vehicle",   institutionKey = "HDFC ERGO")
    )

    // ── Timeline ──────────────────────────────────────────
    fun getTimelineItems() = listOf(
        TimelineItem("t1", "HDFC Home Loan",    "EMI",     32500.0, "Thu, Nov 14", TimelineType.EMI,     true,  "HDFC Bank",      institutionKey = "HDFC Bank"),
        TimelineItem("t2", "LIC Term Insurance","Premium",  9450.0, "Fri, Nov 15", TimelineType.PREMIUM, true,  "LIC",            institutionKey = "LIC"),
        TimelineItem("t3", "ICICI Car Loan",    "EMI",     12000.0, "Sat, Nov 16", TimelineType.EMI,     false, "ICICI Bank",     institutionKey = "ICICI Bank"),
        TimelineItem("t4", "CAMS SIP",          "SIP",      5000.0, "Mon, Nov 18", TimelineType.SIP,     false, "CAMS",           institutionKey = "Mutual Fund"),
        TimelineItem("t5", "SBI Personal Loan", "EMI",      8450.0, "Mon, Nov 18", TimelineType.EMI,     false, "SBI",            institutionKey = "SBI"),
        TimelineItem("t6", "Star Health",       "Premium", 12000.0, "Dec 01",      TimelineType.PREMIUM, false, "Star Health Ins",institutionKey = "Star Health Ins")
    )

    // ── Insights ──────────────────────────────────────────
    fun getInsights() = listOf(
        Insight("in1", "LIC Premium due in 2 days", "₹9,450 due on Nov 15.", InsightType.ALERT,   "Pay now", amount = 9450.0,  institution = "LIC",       institutionKey = "LIC"),
        Insight("in2", "AMC fee detected ₹450",     "Fee charged on mutual fund.", InsightType.INFO, "Review", amount = 450.0, institution = "CAMS",      institutionKey = "Mutual Fund"),
        Insight("in3", "Rebalance portfolio",       "Equity at 72%, above 60% target.", InsightType.WARNING, "View",           institution = "Portfolio", institutionKey = "Equity"),
        Insight("in4", "HDFC Home Loan EMI Due",    "₹32,500 due on Nov 14.", InsightType.ALERT,  "Pay now", amount = 32500.0, institution = "HDFC Bank", institutionKey = "HDFC Bank"),
        Insight("in5", "SIP missed this month",     "CAMS SIP of ₹5,000 missed.", InsightType.WARNING, "View", amount = 5000.0, institution = "CAMS",    institutionKey = "Mutual Fund")
    )

    // ── Vault documents (not consent-gated — user's own uploads) ─────────────
    fun getVaultDocuments() = listOf(
        VaultDocument("v1", "LIC Term Policy",          DocumentCategory.INSURANCE,  "LIC",              "Nov 1, 2023",  true,  "2.4 MB", uploadStatus = UploadStatus.CLOUD,      uploadedAt = System.currentTimeMillis()),
        VaultDocument("v2", "HDFC Home Loan Agreement", DocumentCategory.LOAN,       "HDFC Bank",        "Oct 15, 2023", true,  "1.8 MB", uploadStatus = UploadStatus.CLOUD,      uploadedAt = System.currentTimeMillis()),
        VaultDocument("v3", "PAN Card",                 DocumentCategory.IDENTITY,   "Income Tax Dept",  "Oct 1, 2023",  true,  "0.5 MB", digilockerDocType = "PAN",  digilockerUri = "digilocker://PAN/ABCDE1234F",      uploadStatus = UploadStatus.DIGILOCKER, uploadedAt = System.currentTimeMillis()),
        VaultDocument("v4", "Aadhaar Card",             DocumentCategory.IDENTITY,   "UIDAI",            "Oct 1, 2023",  true,  "0.3 MB", digilockerDocType = "ADHAR", digilockerUri = "digilocker://ADHAR/1234-5678-9012", uploadStatus = UploadStatus.DIGILOCKER, uploadedAt = System.currentTimeMillis()),
        VaultDocument("v5", "ITR FY 2022-23",           DocumentCategory.TAX,        "Income Tax Dept",  "Sep 15, 2023", true,  "1.2 MB", uploadStatus = UploadStatus.CLOUD,      uploadedAt = System.currentTimeMillis()),
        VaultDocument("v6", "CAMS Statement",           DocumentCategory.INVESTMENT, "CAMS",             "Nov 10, 2023", false, "0.8 MB"),
        VaultDocument("v7", "Star Health Policy",       DocumentCategory.INSURANCE,  "Star Health",      "Nov 5, 2023",  true,  "1.5 MB", uploadStatus = UploadStatus.CLOUD,      uploadedAt = System.currentTimeMillis()),
        VaultDocument("v8", "Property Document",        DocumentCategory.MISC,       "Municipal Corp",   "Aug 20, 2023", false, "5.2 MB")
    )
}
