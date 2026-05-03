package com.wealthguardian.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// ──────────────────────────────────────────────────────────
//  Non-persisted display models
// ──────────────────────────────────────────────────────────

data class NetWorth(
    val total: Double = 0.0,
    val cash: Double = 0.0,
    val investments: Double = 0.0,
    val liabilities: Double = 0.0,
    val todayChange: Double = 0.0,
    val lastSynced: String = "Just now",
    val sparklineData: List<Float> = listOf(20f, 22f, 21f, 23f, 22.5f, 24.5f)
)

/** institutionKey: the institution name used for consent matching */
data class Account(
    val id: String, val name: String, val institution: String,
    val type: AccountType, val balance: Double, val lastSynced: String,
    val accountNumber: String = "", val insight: String = "",
    val institutionKey: String = institution   // matches Consent.coveredInstitutions
)
enum class AccountType { BANK, LOAN, INVESTMENT, INSURANCE }

data class LoanAccount(
    val id: String, val name: String, val institution: String,
    val outstanding: Double, val emiAmount: Double, val dueDate: String,
    val tenure: String, val isDue: Boolean = false,
    val institutionKey: String = institution
)

data class Investment(
    val id: String, val name: String, val type: String,
    val currentValue: Double, val invested: Double, val returns: Double,
    val returnsPercent: Double,
    val institutionKey: String = type   // e.g. "Mutual Fund", "Equity", "FD", "PPF"
)

data class InsurancePolicy(
    val id: String, val name: String, val provider: String,
    val coverage: Double, val premium: Double, val dueDate: String,
    val policyType: String, val isActive: Boolean = true,
    val institutionKey: String = provider
)

data class Insight(
    val id: String, val title: String, val description: String,
    val type: InsightType, val actionLabel: String = "View",
    val isSnoozed: Boolean = false,
    val detailBody: String = "",
    val detailAction: String = "",
    val amount: Double = 0.0,
    val institution: String = "",
    val category: String = "",
    val institutionKey: String = institution  // for consent-based filtering
)
enum class InsightType { ALERT, INFO, WARNING, SUCCESS }

data class TimelineItem(
    val id: String, val title: String, val subtitle: String,
    val amount: Double, val dueDate: String, val type: TimelineType,
    val isDue: Boolean = false, val institution: String = "",
    val institutionKey: String = institution
)
enum class TimelineType { EMI, SIP, PREMIUM, MATURITY }

/**
 * Consent model — coveredInstitutions is the list of institution name fragments
 * (used in Account/Loan/Investment/Insurance data) covered by this consent.
 */
data class Consent(
    val id: String, val institution: String, val purpose: String,
    val scope: String, val duration: String, val createdDate: String,
    val expiryDate: String, val isActive: Boolean = true,
    val aaHandle: String = "", val consentHandle: String = "",
    val dataFetchStatus: String = "ACTIVE",
    val coveredInstitutions: List<String> = listOf(institution)
)

// ──────────────────────────────────────────────────────────
//  Room Entities
// ──────────────────────────────────────────────────────────

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey val id: String,
    val name: String, val emoji: String,
    val targetAmount: Double, var currentAmount: Double,
    val category: String,
    val sipAmount: Double = 0.0, val sipDay: Int = 1,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
) {
    val progress: Int get() = ((currentAmount / targetAmount) * 100).toInt().coerceIn(0, 100)
    val remaining: Double get() = (targetAmount - currentAmount).coerceAtLeast(0.0)
}

@Entity(tableName = "vault_documents")
data class VaultDocument(
    @PrimaryKey val id: String,
    val name: String, val category: DocumentCategory,
    val issuer: String, val dateAdded: String,
    var isVerified: Boolean, val fileSize: String,
    val localPath: String = "", val mimeType: String = "",
    val digilockerUri: String = "", val digilockerDocType: String = "",
    val uploadStatus: UploadStatus = UploadStatus.LOCAL,
    val uploadedAt: Long = 0L
)
enum class DocumentCategory { INSURANCE, LOAN, INVESTMENT, IDENTITY, TAX, MISC }
enum class UploadStatus { LOCAL, UPLOADING, CLOUD, DIGILOCKER, FAILED }

@Entity(tableName = "family_members")
data class FamilyMember(
    @PrimaryKey val id: String,
    val name: String, val email: String, val role: FamilyRole,
    val avatarInitials: String, val addedDate: String,
    val phone: String = "",
    val allowedSections: String = "NET_WORTH,ACCOUNTS",
    val inviteAccepted: Boolean = false,
    val inviteSentAt: Long = System.currentTimeMillis()
)
enum class FamilyRole { VIEWER, EMERGENCY, ADMIN }
