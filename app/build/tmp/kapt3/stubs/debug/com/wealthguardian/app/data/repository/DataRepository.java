package com.wealthguardian.app.data.repository;

/**
 * Static data source. Every record now carries an [institutionKey] that matches
 * the institution names declared in [ConsentRepository].coveredInstitutions.
 * Filtering by consent happens in ViewModels via [ConsentRepository.isInstitutionActive].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0004\u00a8\u0006\u0012"}, d2 = {"Lcom/wealthguardian/app/data/repository/DataRepository;", "", "()V", "getBankAccounts", "", "Lcom/wealthguardian/app/data/model/Account;", "getInsights", "Lcom/wealthguardian/app/data/model/Insight;", "getInsurancePolicies", "Lcom/wealthguardian/app/data/model/InsurancePolicy;", "getInvestments", "Lcom/wealthguardian/app/data/model/Investment;", "getLoanAccounts", "Lcom/wealthguardian/app/data/model/LoanAccount;", "getTimelineItems", "Lcom/wealthguardian/app/data/model/TimelineItem;", "getVaultDocuments", "Lcom/wealthguardian/app/data/model/VaultDocument;", "app_debug"})
public final class DataRepository {
    @org.jetbrains.annotations.NotNull()
    public static final com.wealthguardian.app.data.repository.DataRepository INSTANCE = null;
    
    private DataRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.Account> getBankAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.LoanAccount> getLoanAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.Investment> getInvestments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.InsurancePolicy> getInsurancePolicies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.TimelineItem> getTimelineItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.Insight> getInsights() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wealthguardian.app.data.model.VaultDocument> getVaultDocuments() {
        return null;
    }
}