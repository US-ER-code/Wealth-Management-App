package com.wealthguardian.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0011H\u00c6\u0003J\t\u0010,\u001a\u00020\u0013H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0006H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\nH\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\u008b\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u00c6\u0001J\u0013\u00106\u001a\u00020\n2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00108\u001a\u000209H\u00d6\u0001J\t\u0010:\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'\u00a8\u0006;"}, d2 = {"Lcom/wealthguardian/app/data/model/VaultDocument;", "", "id", "", "name", "category", "Lcom/wealthguardian/app/data/model/DocumentCategory;", "issuer", "dateAdded", "isVerified", "", "fileSize", "localPath", "mimeType", "digilockerUri", "digilockerDocType", "uploadStatus", "Lcom/wealthguardian/app/data/model/UploadStatus;", "uploadedAt", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/wealthguardian/app/data/model/DocumentCategory;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/wealthguardian/app/data/model/UploadStatus;J)V", "getCategory", "()Lcom/wealthguardian/app/data/model/DocumentCategory;", "getDateAdded", "()Ljava/lang/String;", "getDigilockerDocType", "getDigilockerUri", "getFileSize", "getId", "()Z", "setVerified", "(Z)V", "getIssuer", "getLocalPath", "getMimeType", "getName", "getUploadStatus", "()Lcom/wealthguardian/app/data/model/UploadStatus;", "getUploadedAt", "()J", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
@androidx.room.Entity(tableName = "vault_documents")
public final class VaultDocument {
    @androidx.room.PrimaryKey()
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wealthguardian.app.data.model.DocumentCategory category = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String issuer = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dateAdded = null;
    private boolean isVerified;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fileSize = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String localPath = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String mimeType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String digilockerUri = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String digilockerDocType = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wealthguardian.app.data.model.UploadStatus uploadStatus = null;
    private final long uploadedAt = 0L;
    
    public VaultDocument(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.DocumentCategory category, @org.jetbrains.annotations.NotNull()
    java.lang.String issuer, @org.jetbrains.annotations.NotNull()
    java.lang.String dateAdded, boolean isVerified, @org.jetbrains.annotations.NotNull()
    java.lang.String fileSize, @org.jetbrains.annotations.NotNull()
    java.lang.String localPath, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType, @org.jetbrains.annotations.NotNull()
    java.lang.String digilockerUri, @org.jetbrains.annotations.NotNull()
    java.lang.String digilockerDocType, @org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.UploadStatus uploadStatus, long uploadedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthguardian.app.data.model.DocumentCategory getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIssuer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateAdded() {
        return null;
    }
    
    public final boolean isVerified() {
        return false;
    }
    
    public final void setVerified(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFileSize() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLocalPath() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMimeType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDigilockerUri() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDigilockerDocType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthguardian.app.data.model.UploadStatus getUploadStatus() {
        return null;
    }
    
    public final long getUploadedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthguardian.app.data.model.UploadStatus component12() {
        return null;
    }
    
    public final long component13() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthguardian.app.data.model.DocumentCategory component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthguardian.app.data.model.VaultDocument copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.DocumentCategory category, @org.jetbrains.annotations.NotNull()
    java.lang.String issuer, @org.jetbrains.annotations.NotNull()
    java.lang.String dateAdded, boolean isVerified, @org.jetbrains.annotations.NotNull()
    java.lang.String fileSize, @org.jetbrains.annotations.NotNull()
    java.lang.String localPath, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType, @org.jetbrains.annotations.NotNull()
    java.lang.String digilockerUri, @org.jetbrains.annotations.NotNull()
    java.lang.String digilockerDocType, @org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.UploadStatus uploadStatus, long uploadedAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}