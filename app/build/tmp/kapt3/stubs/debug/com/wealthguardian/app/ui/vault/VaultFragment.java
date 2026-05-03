package com.wealthguardian.app.ui.vault;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u001a\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\"\u001a\u00020\u001fH\u0002J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\u001fH\u0002J\b\u0010\'\u001a\u00020\u001fH\u0002J\b\u0010(\u001a\u00020\u001fH\u0002J\u0010\u0010)\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u001fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006/"}, d2 = {"Lcom/wealthguardian/app/ui/vault/VaultFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_b", "Lcom/wealthguardian/app/databinding/FragmentVaultBinding;", "b", "getB", "()Lcom/wealthguardian/app/databinding/FragmentVaultBinding;", "filePicker", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "pendingDocCategory", "Lcom/wealthguardian/app/data/model/DocumentCategory;", "pendingDocName", "", "vm", "Lcom/wealthguardian/app/ui/vault/VaultViewModel;", "getVm", "()Lcom/wealthguardian/app/ui/vault/VaultViewModel;", "vm$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "i", "Landroid/view/LayoutInflater;", "c", "Landroid/view/ViewGroup;", "s", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "v", "pickFileFromDevice", "showDeleteConfirm", "doc", "Lcom/wealthguardian/app/data/model/VaultDocument;", "showDigilockerAuthDialog", "showDigilockerDialog", "showDigilockerDocList", "showDocumentDetail", "showNameDocumentDialog", "uri", "Landroid/net/Uri;", "suggestedName", "showUploadChoiceDialog", "app_debug"})
public final class VaultFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.wealthguardian.app.databinding.FragmentVaultBinding _b;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String pendingDocName = "";
    @org.jetbrains.annotations.NotNull()
    private com.wealthguardian.app.data.model.DocumentCategory pendingDocCategory = com.wealthguardian.app.data.model.DocumentCategory.MISC;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> filePicker = null;
    
    public VaultFragment() {
        super();
    }
    
    private final com.wealthguardian.app.databinding.FragmentVaultBinding getB() {
        return null;
    }
    
    private final com.wealthguardian.app.ui.vault.VaultViewModel getVm() {
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
    
    private final void showUploadChoiceDialog() {
    }
    
    private final void pickFileFromDevice() {
    }
    
    private final void showNameDocumentDialog(android.net.Uri uri, java.lang.String suggestedName) {
    }
    
    private final void showDigilockerDialog() {
    }
    
    private final void showDigilockerAuthDialog() {
    }
    
    private final void showDigilockerDocList() {
    }
    
    private final void showDocumentDetail(com.wealthguardian.app.data.model.VaultDocument doc) {
    }
    
    private final void showDeleteConfirm(com.wealthguardian.app.data.model.VaultDocument doc) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}