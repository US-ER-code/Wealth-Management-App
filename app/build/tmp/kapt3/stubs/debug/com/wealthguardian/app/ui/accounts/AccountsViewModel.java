package com.wealthguardian.app.ui.accounts;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000fH\u0002J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020\"J\u0006\u0010%\u001a\u00020\"J\b\u0010&\u001a\u00020\"H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R#\u0010\u001c\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013\u00a8\u0006\'"}, d2 = {"Lcom/wealthguardian/app/ui/accounts/AccountsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_bank", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/wealthguardian/app/data/model/Account;", "_insure", "Lcom/wealthguardian/app/data/model/InsurancePolicy;", "_invest", "Lcom/wealthguardian/app/data/model/Investment;", "_loans", "Lcom/wealthguardian/app/data/model/LoanAccount;", "_summary", "Lkotlin/Pair;", "", "bankAccounts", "Landroidx/lifecycle/LiveData;", "getBankAccounts", "()Landroidx/lifecycle/LiveData;", "currentTab", "", "insurance", "getInsurance", "investments", "getInvestments", "loanAccounts", "getLoanAccounts", "summary", "getSummary", "isActive", "", "institutionKey", "loadBankAccounts", "", "loadInsurance", "loadInvestments", "loadLoanAccounts", "refreshCurrentTab", "app_debug"})
public final class AccountsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.Account>> _bank = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Account>> bankAccounts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.LoanAccount>> _loans = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.LoanAccount>> loanAccounts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.Investment>> _invest = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Investment>> investments = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.InsurancePolicy>> _insure = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.InsurancePolicy>> insurance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, java.lang.String>> _summary = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Pair<java.lang.String, java.lang.String>> summary = null;
    private int currentTab = 0;
    
    public AccountsViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Account>> getBankAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.LoanAccount>> getLoanAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Investment>> getInvestments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.InsurancePolicy>> getInsurance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Pair<java.lang.String, java.lang.String>> getSummary() {
        return null;
    }
    
    private final void refreshCurrentTab() {
    }
    
    public final void loadBankAccounts() {
    }
    
    public final void loadLoanAccounts() {
    }
    
    public final void loadInvestments() {
    }
    
    public final void loadInsurance() {
    }
    
    private final boolean isActive(java.lang.String institutionKey) {
        return false;
    }
}