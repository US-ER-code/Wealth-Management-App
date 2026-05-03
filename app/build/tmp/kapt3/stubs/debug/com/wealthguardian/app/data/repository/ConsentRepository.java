package com.wealthguardian.app.data.repository;

/**
 * Singleton that holds the live list of AA consents.
 * ALL ViewModels that need consent-aware data observe [consentsFlow].
 * Revoking here automatically propagates to Home, Accounts, Timeline, Insights, etc.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fJ\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\fR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/wealthguardian/app/data/repository/ConsentRepository;", "", "()V", "_consentsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/wealthguardian/app/data/model/Consent;", "activeConsents", "getActiveConsents", "()Ljava/util/List;", "activeInstitutions", "", "", "getActiveInstitutions", "()Ljava/util/Set;", "consentsFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getConsentsFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "seed", "addConsent", "", "consent", "isInstitutionActive", "", "institutionName", "revoke", "id", "app_debug"})
public final class ConsentRepository {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.wealthguardian.app.data.model.Consent> seed = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.wealthguardian.app.data.model.Consent>> _consentsFlow = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.util.List<com.wealthguardian.app.data.model.Consent>> consentsFlow = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.wealthguardian.app.data.repository.ConsentRepository INSTANCE = null;
    
    private ConsentRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.wealthguardian.app.data.model.Consent>> getConsentsFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.Consent> getActiveConsents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getActiveInstitutions() {
        return null;
    }
    
    public final void revoke(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    public final void addConsent(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.Consent consent) {
    }
    
    /**
     * Check if a given institution name is covered by an active consent.
     * Does a case-insensitive substring match against all coveredInstitutions.
     */
    public final boolean isInstitutionActive(@org.jetbrains.annotations.NotNull()
    java.lang.String institutionName) {
        return false;
    }
}