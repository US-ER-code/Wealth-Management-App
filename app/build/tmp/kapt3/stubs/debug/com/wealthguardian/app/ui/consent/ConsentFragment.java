package com.wealthguardian.app.ui.consent;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u001a\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006$"}, d2 = {"Lcom/wealthguardian/app/ui/consent/ConsentFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_b", "Lcom/wealthguardian/app/databinding/FragmentConsentBinding;", "b", "getB", "()Lcom/wealthguardian/app/databinding/FragmentConsentBinding;", "homeVm", "Lcom/wealthguardian/app/ui/home/HomeViewModel;", "getHomeVm", "()Lcom/wealthguardian/app/ui/home/HomeViewModel;", "homeVm$delegate", "Lkotlin/Lazy;", "vm", "Lcom/wealthguardian/app/ui/consent/ConsentViewModel;", "getVm", "()Lcom/wealthguardian/app/ui/consent/ConsentViewModel;", "vm$delegate", "onCreateView", "Landroid/view/View;", "i", "Landroid/view/LayoutInflater;", "c", "Landroid/view/ViewGroup;", "s", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "v", "showAAConsentDialog", "showConsentDetail", "consent", "Lcom/wealthguardian/app/data/model/Consent;", "showRevokeDialog", "app_debug"})
public final class ConsentFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.wealthguardian.app.databinding.FragmentConsentBinding _b;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy homeVm$delegate = null;
    
    public ConsentFragment() {
        super();
    }
    
    private final com.wealthguardian.app.databinding.FragmentConsentBinding getB() {
        return null;
    }
    
    private final com.wealthguardian.app.ui.consent.ConsentViewModel getVm() {
        return null;
    }
    
    private final com.wealthguardian.app.ui.home.HomeViewModel getHomeVm() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater i, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup c, @org.jetbrains.annotations.Nullable()
    android.os.Bundle s) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View v, @org.jetbrains.annotations.Nullable()
    android.os.Bundle s) {
    }
    
    private final void showRevokeDialog(com.wealthguardian.app.data.model.Consent consent) {
    }
    
    private final void showAAConsentDialog() {
    }
    
    private final void showConsentDetail(com.wealthguardian.app.data.model.Consent consent) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}