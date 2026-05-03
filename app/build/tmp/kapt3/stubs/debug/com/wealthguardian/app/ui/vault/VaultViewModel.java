package com.wealthguardian.app.ui.vault;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0012J\u0016\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u0007H\u0082@\u00a2\u0006\u0002\u0010%J\u001c\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u00112\b\u0010\'\u001a\u0004\u0018\u00010(J\u000e\u0010)\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\fJ\u0010\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020\u0007H\u0002J&\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020(R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001f\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00160\u00160\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\tR\u001f\u0010\u001a\u001a\u0010\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u001b0\u001b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\t\u00a8\u00063"}, d2 = {"Lcom/wealthguardian/app/ui/vault/VaultViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/wealthguardian/app/data/db/AppDatabase;", "(Lcom/wealthguardian/app/data/db/AppDatabase;)V", "aaConsentHandle", "Landroidx/lifecycle/MutableLiveData;", "", "getAaConsentHandle", "()Landroidx/lifecycle/MutableLiveData;", "digilockerDocs", "", "Lcom/wealthguardian/app/data/api/DigilockerDocument;", "getDigilockerDocs", "digilockerToken", "getDigilockerToken", "docs", "Landroidx/lifecycle/LiveData;", "Lcom/wealthguardian/app/data/model/VaultDocument;", "getDocs", "()Landroidx/lifecycle/LiveData;", "isUploading", "", "kotlin.jvm.PlatformType", "statusMessage", "getStatusMessage", "uploadProgress", "", "getUploadProgress", "authenticateDigilocker", "", "mobile", "otp", "deleteDocument", "doc", "fetchDigilockerList", "token", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filter", "cat", "Lcom/wealthguardian/app/data/model/DocumentCategory;", "importDigilockerDoc", "mapDocType", "type", "uploadPhysicalDocument", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "docName", "category", "app_debug"})
public final class VaultViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.wealthguardian.app.data.db.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.VaultDocument>> docs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isUploading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> uploadProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> statusMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> digilockerToken = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.api.DigilockerDocument>> digilockerDocs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> aaConsentHandle = null;
    
    public VaultViewModel(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.db.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.VaultDocument>> getDocs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isUploading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getUploadProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getStatusMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getDigilockerToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.wealthguardian.app.data.api.DigilockerDocument>> getDigilockerDocs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getAaConsentHandle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.VaultDocument>> filter(@org.jetbrains.annotations.Nullable()
    com.wealthguardian.app.data.model.DocumentCategory cat) {
        return null;
    }
    
    /**
     * Upload physical document from device file picker
     */
    public final void uploadPhysicalDocument(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    java.lang.String docName, @org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.DocumentCategory category) {
    }
    
    /**
     * Authenticate with DigiLocker
     */
    public final void authenticateDigilocker(@org.jetbrains.annotations.NotNull()
    java.lang.String mobile, @org.jetbrains.annotations.NotNull()
    java.lang.String otp) {
    }
    
    private final java.lang.Object fetchDigilockerList(java.lang.String token, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Pull a specific DigiLocker document and save to vault
     */
    public final void importDigilockerDoc(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.api.DigilockerDocument doc) {
    }
    
    public final void deleteDocument(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.VaultDocument doc) {
    }
    
    private final com.wealthguardian.app.data.model.DocumentCategory mapDocType(java.lang.String type) {
        return null;
    }
}