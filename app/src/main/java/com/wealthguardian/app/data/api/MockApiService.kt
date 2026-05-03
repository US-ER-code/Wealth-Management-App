package com.wealthguardian.app.data.api

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.util.UUID

// ──────────────────────────────────────────────────────────
//  Response models
// ──────────────────────────────────────────────────────────
data class AAConsentInitResponse(val consentHandle: String, val redirectUrl: String, val status: String)
data class AAConsentStatusResponse(val consentHandle: String, val status: String, val approvedAt: String?)
data class AADataFetchResponse(val sessionId: String, val status: String, val accounts: List<AAAccount>)
data class AAAccount(val fipId: String, val accountType: String, val maskedAccNumber: String, val balance: Double)

data class DigilockerAuthResponse(val accessToken: String, val userId: String, val name: String)
data class DigilockerDocument(val docType: String, val docName: String, val issuedBy: String, val uri: String, val fileSize: String)
data class DigilockerListResponse(val documents: List<DigilockerDocument>)
data class DigilockerPullResponse(val success: Boolean, val uri: String, val base64Data: String, val mimeType: String)

data class VaultUploadResponse(val success: Boolean, val docId: String, val cloudUrl: String, val isVerified: Boolean)

// ──────────────────────────────────────────────────────────
//  Mock API Service – simulates real network latency + responses
// ──────────────────────────────────────────────────────────
object MockApiService {
    private val gson = Gson()
    private const val TAG = "MockAPI"

    // ── Account Aggregator (AA) Consent Flow ─────────────────

    /**
     * Step 1: Initiate AA consent – user enters VPA / mobile / AA handle.
     * In real AA: POST /Consent to AA endpoint, returns consentHandle + redirect URL.
     */
    suspend fun initiateAAConsent(
        aaHandle: String,          // e.g. user@onemoney
        fiuId: String = "WEALTHGUARDIAN",
        purpose: String = "Account aggregation for personal finance management",
        dateRangeMonths: Int = 12
    ): Result<AAConsentInitResponse> {
        Log.d(TAG, "AA: Initiating consent for $aaHandle")
        delay(1500) // Simulate network

        // Validate handle format
        if (!aaHandle.contains("@")) {
            return Result.failure(Exception("Invalid AA handle. Format: user@onemoney or user@finvu"))
        }

        val consentHandle = "CH-${UUID.randomUUID().toString().take(8).uppercase()}"
        val response = AAConsentInitResponse(
            consentHandle = consentHandle,
            redirectUrl = "https://mock-aa.sandbox.sahamati.org.in/consent?handle=$consentHandle&fiu=$fiuId",
            status = "PENDING"
        )
        Log.d(TAG, "AA: Consent initiated → $consentHandle")
        return Result.success(response)
    }

    /**
     * Step 2: Poll consent status – simulate user approving on their AA app.
     * In real AA: GET /Consent/{consentHandle}/status
     */
    suspend fun pollConsentStatus(consentHandle: String): Result<AAConsentStatusResponse> {
        Log.d(TAG, "AA: Polling consent status for $consentHandle")
        delay(2000) // Simulate polling

        // Simulate approval (in reality user approves via AA app)
        val response = AAConsentStatusResponse(
            consentHandle = consentHandle,
            status = "APPROVED",
            approvedAt = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.getDefault())
                .format(java.util.Date())
        )
        return Result.success(response)
    }

    /**
     * Step 3: Fetch data after consent approved.
     * In real AA: POST /FI/request → sessionId, then GET /FI/fetch/{sessionId}
     */
    suspend fun fetchFinancialData(consentHandle: String, aaHandle: String): Result<AADataFetchResponse> {
        Log.d(TAG, "AA: Fetching FI data for $consentHandle")
        delay(2500)

        val fipPrefix = aaHandle.substringAfter("@").lowercase()
        val mockAccounts = when {
            fipPrefix.contains("onemoney") || fipPrefix.contains("finvu") -> listOf(
                AAAccount("HDFC0001234", "SAVINGS", "****4821", 185000.0),
                AAAccount("HDFC0001234", "LOAN", "****2210", -2450000.0),
                AAAccount("ICICI0002345", "CREDIT_CARD", "****9988", -45000.0)
            )
            fipPrefix.contains("setu") || fipPrefix.contains("cams") -> listOf(
                AAAccount("CAMS0001", "MUTUAL_FUND", "****0001", 980000.0),
                AAAccount("ZERODHA0001", "DEMAT", "****1234", 540000.0)
            )
            else -> listOf(
                AAAccount("GENERIC0001", "SAVINGS", "****0000", 100000.0)
            )
        }

        return Result.success(AADataFetchResponse(
            sessionId = "SES-${UUID.randomUUID().toString().take(8).uppercase()}",
            status = "READY",
            accounts = mockAccounts
        ))
    }

    // ── DigiLocker Flow ──────────────────────────────────────

    /**
     * DigiLocker OAuth – user authorizes via DigiLocker.
     * Real: GET https://api.digitallocker.gov.in/public/oauth2/1/authorize
     */
    suspend fun authenticateDigilocker(
        mobileNumber: String,
        otp: String
    ): Result<DigilockerAuthResponse> {
        Log.d(TAG, "DigiLocker: Authenticating $mobileNumber")
        delay(1800)

        if (otp.length != 6) return Result.failure(Exception("Invalid OTP. Enter 6-digit OTP."))
        if (otp != "123456") return Result.failure(Exception("Incorrect OTP. Try 123456 for demo."))

        return Result.success(DigilockerAuthResponse(
            accessToken = "DL-${UUID.randomUUID().toString().take(16)}",
            userId = "DL-USER-${mobileNumber.takeLast(4)}",
            name = "Rajesh Kumar"
        ))
    }

    /**
     * List documents available in user's DigiLocker.
     * Real: GET https://api.digitallocker.gov.in/public/oauth2/1/files/issued
     */
    suspend fun listDigilockerDocuments(accessToken: String): Result<DigilockerListResponse> {
        Log.d(TAG, "DigiLocker: Listing documents")
        delay(1200)

        val docs = listOf(
            DigilockerDocument("ADHAR", "Aadhaar Card", "UIDAI", "in.gov.uidai.aadhaar/ADHAR/1234-5678-9012", "0.3 MB"),
            DigilockerDocument("PAN", "PAN Card", "Income Tax Department", "in.gov.india.pan/PAN/ABCDE1234F", "0.5 MB"),
            DigilockerDocument("DL", "Driving Licence", "MoRTH", "in.gov.morth.dl/DL/MH12-AB-2020-1234567", "0.8 MB"),
            DigilockerDocument("VOTERID", "Voter ID Card", "ECI", "in.gov.eci.epic/VOTERID/MBR1234567", "0.4 MB"),
            DigilockerDocument("ITR", "ITR AY 2023-24", "Income Tax Department", "in.gov.itd.itr/ITR/2324/1234567890", "1.2 MB"),
            DigilockerDocument("INSURANCE", "LIC Policy Certificate", "LIC", "in.com.licindia.policy/INSURANCE/123456789", "2.1 MB")
        )
        return Result.success(DigilockerListResponse(docs))
    }

    /**
     * Pull/download a specific document from DigiLocker.
     * Real: GET https://api.digitallocker.gov.in/public/oauth2/1/file/{uri}
     */
    suspend fun pullDigilockerDocument(accessToken: String, uri: String): Result<DigilockerPullResponse> {
        Log.d(TAG, "DigiLocker: Pulling document $uri")
        delay(2000)

        // Return mock base64 placeholder (real would be PDF bytes)
        return Result.success(DigilockerPullResponse(
            success = true,
            uri = uri,
            base64Data = "JVBERi0xLjQKJeLjz9MKMSAwIG9iago8PAovVHlwZSAvQ2F0YWxvZwo+PgplbmRvYmoK", // minimal PDF header
            mimeType = "application/pdf"
        ))
    }

    // ── Vault / Document Upload ──────────────────────────────

    /**
     * Upload a physical document to the backend / S3 equivalent.
     * Real: POST /vault/upload with multipart form data.
     */
    suspend fun uploadDocumentToVault(
        docName: String,
        category: String,
        fileBytes: ByteArray,
        mimeType: String
    ): Result<VaultUploadResponse> {
        Log.d(TAG, "Vault: Uploading $docName (${fileBytes.size} bytes)")
        delay(2000 + (fileBytes.size / 10000).toLong().coerceAtMost(3000)) // Simulate upload time

        if (fileBytes.isEmpty()) return Result.failure(Exception("File is empty"))
        if (fileBytes.size > 10 * 1024 * 1024) return Result.failure(Exception("File too large. Max 10 MB."))

        val docId = "DOC-${UUID.randomUUID().toString().take(8).uppercase()}"
        // Simulate auto-verification for known doc types
        val autoVerified = category in listOf("IDENTITY", "TAX") ||
            docName.contains("Aadhaar", true) || docName.contains("PAN", true)

        return Result.success(VaultUploadResponse(
            success = true,
            docId = docId,
            cloudUrl = "https://vault.wealthguardian.in/docs/$docId",
            isVerified = autoVerified
        ))
    }

    /**
     * Upload a DigiLocker-pulled document to our vault backend.
     * Tags it as DIGILOCKER source with verified = true.
     */
    suspend fun uploadDigilockerDocToVault(
        docName: String,
        category: String,
        digilockerUri: String,
        base64Data: String
    ): Result<VaultUploadResponse> {
        Log.d(TAG, "Vault: Uploading from DigiLocker $docName")
        delay(1500)

        val docId = "DL-DOC-${UUID.randomUUID().toString().take(8).uppercase()}"
        return Result.success(VaultUploadResponse(
            success = true,
            docId = docId,
            cloudUrl = "https://vault.wealthguardian.in/docs/digilocker/$docId",
            isVerified = true  // DigiLocker docs are always government-verified
        ))
    }
}
