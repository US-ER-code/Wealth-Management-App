package com.wealthguardian.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \t2\u00020\u0001:\u0002\t\nB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\u000b"}, d2 = {"Lcom/wealthguardian/app/data/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "familyDao", "Lcom/wealthguardian/app/data/db/FamilyDao;", "goalDao", "Lcom/wealthguardian/app/data/db/GoalDao;", "vaultDao", "Lcom/wealthguardian/app/data/db/VaultDao;", "Companion", "SeedCallback", "app_debug"})
@androidx.room.Database(entities = {com.wealthguardian.app.data.model.Goal.class, com.wealthguardian.app.data.model.VaultDocument.class, com.wealthguardian.app.data.model.FamilyMember.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.wealthguardian.app.data.db.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.wealthguardian.app.data.db.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.wealthguardian.app.data.db.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wealthguardian.app.data.db.GoalDao goalDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wealthguardian.app.data.db.VaultDao vaultDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wealthguardian.app.data.db.FamilyDao familyDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/wealthguardian/app/data/db/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/wealthguardian/app/data/db/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wealthguardian.app.data.db.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/wealthguardian/app/data/db/AppDatabase$SeedCallback;", "Landroidx/room/RoomDatabase$Callback;", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_debug"})
    public static final class SeedCallback extends androidx.room.RoomDatabase.Callback {
        @org.jetbrains.annotations.NotNull()
        private final android.content.Context ctx = null;
        
        public SeedCallback(@org.jetbrains.annotations.NotNull()
        android.content.Context ctx) {
            super();
        }
        
        @java.lang.Override()
        public void onCreate(@org.jetbrains.annotations.NotNull()
        androidx.sqlite.db.SupportSQLiteDatabase db) {
        }
    }
}