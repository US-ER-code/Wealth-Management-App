package com.wealthguardian.app.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\b\u0010\u001f\u001a\u00020\u001dH\u0002J\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"J\u001a\u0010#\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\"0$2\u0006\u0010%\u001a\u00020\"H\u0002R#\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u001f\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u00140\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\tR\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\f0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\t\u00a8\u0006&"}, d2 = {"Lcom/wealthguardian/app/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "accounts", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/wealthguardian/app/data/model/AccountType;", "", "getAccounts", "()Landroidx/lifecycle/MutableLiveData;", "consents", "Landroidx/lifecycle/LiveData;", "", "Lcom/wealthguardian/app/data/model/Consent;", "getConsents", "()Landroidx/lifecycle/LiveData;", "insights", "Lcom/wealthguardian/app/data/model/Insight;", "getInsights", "isLoading", "", "kotlin.jvm.PlatformType", "netWorth", "Lcom/wealthguardian/app/data/model/NetWorth;", "getNetWorth", "timelineItems", "Lcom/wealthguardian/app/data/model/TimelineItem;", "getTimelineItems", "loadData", "", "onConsentChanged", "recompute", "snoozeInsight", "id", "", "containsKey", "", "key", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.wealthguardian.app.data.model.NetWorth> netWorth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.TimelineItem>> timelineItems = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.Insight>> insights = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<com.wealthguardian.app.data.model.AccountType, java.lang.Double>> accounts = null;
    
    /**
     * Live consent list — observed by HomeFragment for the consent card
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Consent>> consents = null;
    
    public HomeViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.wealthguardian.app.data.model.NetWorth> getNetWorth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.TimelineItem>> getTimelineItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.model.Insight>> getInsights() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.Map<com.wealthguardian.app.data.model.AccountType, java.lang.Double>> getAccounts() {
        return null;
    }
    
    /**
     * Live consent list — observed by HomeFragment for the consent card
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Consent>> getConsents() {
        return null;
    }
    
    public final void loadData() {
    }
    
    /**
     * Recompute all consent-gated data using the current active institutions.
     * Called on first load AND whenever consent state changes.
     */
    private final void recompute() {
    }
    
    /**
     * Triggered from ConsentFragment (or anywhere) after a consent is revoked.
     * Immediately recomputes all displayed data.
     */
    public final void onConsentChanged() {
    }
    
    public final void snoozeInsight(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    private final boolean containsKey(java.util.Set<java.lang.String> $this$containsKey, java.lang.String key) {
        return false;
    }
}