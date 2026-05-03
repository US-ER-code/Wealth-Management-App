# WealthGuardian 🛡️

> A personal finance management Android app that aggregates accounts, manages documents, tracks goals, and enables family-level access — all powered by the Account Aggregator (AA) framework.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Account Aggregator Integration](#account-aggregator-integration)
- [Key Design Decisions](#key-design-decisions)
- [Contributing](#contributing)

---

## Overview

WealthGuardian is a native Android application built with Kotlin that gives users a unified view of their financial life. It connects to banks, lenders, and investment platforms via the **Account Aggregator (AA) framework**, securely stores personal documents, and lets users grant controlled access to family members.

> ⚠️ **Note:** This is a demo/prototype build. All AA and DigiLocker API calls are simulated via `MockApiService`. No real financial data is transmitted.

---

## Features

### 🏠 Home Dashboard
- **Net worth card** with a live sparkline chart showing portfolio trend
- **Timeline** of upcoming EMIs, SIPs, insurance premiums, and maturities
- **AI Insights** — smart alerts and warnings (snoozable)
- Quick-action shortcuts to all major features

### 🏦 Accounts
- Aggregated view of bank accounts, loans, investments, and insurance
- Consent-aware — data disappears instantly when a consent is revoked
- Loan EMI tracker with due-date highlighting
- Investment breakdown across Mutual Funds, Equity, FD, and PPF

### 🔗 Consent Manager
- Link financial institutions via AA handle
- Define data scope (account type) and duration per consent
- Revoke any consent with a single tap — all linked data is removed live across every screen
- Consent detail view with handle, expiry, and covered institutions

### 🗄️ Vault
- Upload documents from device (PDF, JPG, PNG)
- Import documents directly from **DigiLocker** (OTP-authenticated)
- Filter by category: Identity, Tax, Insurance, Property, Medical, Other
- Full-text search across document names

### 👨‍👩‍👧 Family Access
- Invite family members by email
- Assign roles: **Viewer**, **Emergency Contact**, or **Admin**
- Grant granular section-level access (Vault, Goals, AA Consents, Accounts)
- Manage and revoke access at any time

### 🎯 Goals
- Create financial goals with a target amount and deadline
- Track progress as a percentage with a visual progress bar
- Goals persist across sessions via Room DB

---

## Architecture

WealthGuardian follows **MVVM + Repository** pattern with a clean layered architecture.

```
┌─────────────────────────────────────────┐
│              UI Layer                   │
│  SplashActivity · OnboardingActivity    │
│  MainActivity (NavHostFragment)         │
│  HomeFragment · AccountsFragment        │
│  ConsentFragment · VaultFragment        │
│  FamilyFragment · GoalsFragment         │
│  InsightDetailActivity                  │
├─────────────────────────────────────────┤
│           ViewModel Layer               │
│  HomeViewModel · AccountsViewModel      │
│  ConsentViewModel · GoalsViewModel      │
├─────────────────────────────────────────┤
│          Repository Layer               │
│  ConsentRepository (Singleton/StateFlow)│
│  DataRepository                         │
├─────────────────────────────────────────┤
│            Data Layer                   │
│  Room DB: VaultDao · GoalDao · FamilyDao│
│  MockApiService (AA + DigiLocker)       │
│  SharedPreferences (first-launch flag)  │
└─────────────────────────────────────────┘
```

### Consent propagation

`ConsentRepository` is a **singleton** that exposes active consents via a Kotlin `StateFlow`. When a consent is revoked, the flow emits the updated list — all ViewModels observing it (Home, Accounts, Consent) automatically filter their data without any manual refresh.

---

## Tech Stack

| Category | Library / Tool |
|---|---|
| Language | Kotlin |
| UI | Android Views · ViewBinding · Material 3 |
| Navigation | Jetpack Navigation Component |
| Architecture | MVVM · ViewModel · LiveData · StateFlow |
| Database | Room 2.6 |
| Async | Kotlin Coroutines |
| Charts | MPAndroidChart |
| Networking | OkHttp 4 · Retrofit 2 · Gson |
| Min SDK | API 24 (Android 7.0) |
| Target SDK | API 34 (Android 14) |
| Build | Gradle · Kotlin KAPT |

---

## Project Structure

```
app/src/main/java/com/wealthguardian/app/
├── WealthGuardianApp.kt          # Application class — Room DB init
├── data/
│   ├── api/
│   │   └── MockApiService.kt     # Simulated AA & DigiLocker calls
│   ├── db/
│   │   ├── AppDatabase.kt        # Room database
│   │   ├── Converters.kt         # TypeConverters for complex objects
│   │   ├── FamilyDao.kt
│   │   ├── GoalDao.kt
│   │   └── VaultDao.kt
│   ├── model/
│   │   └── Models.kt             # All data classes & enums
│   └── repository/
│       ├── ConsentRepository.kt  # Singleton · StateFlow · live revocation
│       └── DataRepository.kt     # Accounts, goals, family data
└── ui/
    ├── MainActivity.kt           # Bottom nav host
    ├── accounts/
    │   ├── AccountsFragment.kt
    │   ├── AccountsViewModel.kt
    │   └── AccountAdapters.kt
    ├── consent/
    │   └── ConsentFragment.kt
    ├── family/
    │   └── FamilyFragment.kt
    ├── goals/
    │   └── GoalsViewModel.kt
    ├── home/
    │   ├── HomeFragment.kt
    │   ├── HomeViewModel.kt
    │   ├── InsightAdapter.kt
    │   └── TimelineAdapter.kt
    ├── insights/
    │   └── InsightDetailActivity.kt
    ├── onboarding/
    │   ├── SplashActivity.kt
    │   ├── OnboardingActivity.kt
    │   └── OnboardingAdapter.kt
    └── vault/
        └── VaultFragment.kt
```

---

## Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 34

### Clone & run

```bash
git clone https://github.com/your-org/WealthGuardian.git
cd WealthGuardian
```

Open the project in Android Studio, let Gradle sync, then run on an emulator or device (API 24+).

No API keys are required — all network calls are mocked.

### Build variants

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

---

## Account Aggregator Integration

WealthGuardian is designed around India's **Account Aggregator (AA) framework**. The consent flow mirrors a real AA integration:

1. User enters their **AA handle** (e.g. `user@onemoney`)
2. Selects **institution** and **data scope** (account type)
3. Sets **consent duration**
4. AA framework authenticates and returns a **consent handle**
5. Data is fetched and displayed across all relevant screens

Supported AA providers (mocked): `oneMoney`, `finvu`, `setu`

In production, step 4 would redirect to the AA provider's app or webview for OTP-based approval.

---

## Key Design Decisions

**Consent-first data model** — every account, loan, investment, and insight carries an `institutionKey`. When a consent is revoked, data is filtered client-side in real time without a network round-trip.

**Single-activity navigation** — `MainActivity` hosts all fragments via Jetpack Navigation. Only `SplashActivity`, `OnboardingActivity`, and `InsightDetailActivity` are separate activities.

**HomeViewModel scoped to Activity** — the Home ViewModel is scoped to `requireActivity()` so it survives fragment recreation and can be shared across tabs for consent-aware data updates.

**Room for user-generated content only** — only Vault documents, Goals, and Family members are persisted locally. Account/financial data is always fetched fresh from the AA layer (or mock) and is not cached.

---

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add your feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

Please follow the existing code style and ensure no new lint warnings are introduced.

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
