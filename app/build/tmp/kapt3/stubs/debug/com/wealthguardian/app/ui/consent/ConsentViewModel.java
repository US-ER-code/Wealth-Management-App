package com.wealthguardian.app.ui.consent;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J,\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0005J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u000fR\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r\u00a8\u0006\u0019"}, d2 = {"Lcom/wealthguardian/app/ui/consent/ConsentViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "consents", "Landroidx/lifecycle/LiveData;", "", "Lcom/wealthguardian/app/data/model/Consent;", "getConsents", "()Landroidx/lifecycle/LiveData;", "isLoading", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "()Landroidx/lifecycle/MutableLiveData;", "statusMsg", "", "getStatusMsg", "initiateAAConsent", "", "aaHandle", "institution", "scope", "coveredInstitutions", "revoke", "id", "app_debug"})
public final class ConsentViewModel extends androidx.lifecycle.ViewModel {
    
    /**
     * Mirror ConsentRepository as LiveData for the fragment
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Consent>> consents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> statusMsg = null;
    
    public ConsentViewModel() {
        super();
    }
    
    /**
     * Mirror ConsentRepository as LiveData for the fragment
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Consent>> getConsents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getStatusMsg() {
        return null;
    }
    
    public final void revoke(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    public final void initiateAAConsent(@org.jetbrains.annotations.NotNull()
    java.lang.String aaHandle, @org.jetbrains.annotations.NotNull()
    java.lang.String institution, @org.jetbrains.annotations.NotNull()
    java.lang.String scope, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> coveredInstitutions) {
    }
}