package com.wealthguardian.app;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\f"}, d2 = {"Lcom/wealthguardian/app/WealthGuardianApp;", "Landroid/app/Application;", "()V", "database", "Lcom/wealthguardian/app/data/db/AppDatabase;", "getDatabase", "()Lcom/wealthguardian/app/data/db/AppDatabase;", "database$delegate", "Lkotlin/Lazy;", "onCreate", "", "Companion", "app_debug"})
public final class WealthGuardianApp extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    private static com.wealthguardian.app.WealthGuardianApp instance;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "wealth_guardian_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FIRST_LAUNCH = "first_launch";
    @org.jetbrains.annotations.NotNull()
    public static final com.wealthguardian.app.WealthGuardianApp.Companion Companion = null;
    
    public WealthGuardianApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthguardian.app.data.db.AppDatabase getDatabase() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/wealthguardian/app/WealthGuardianApp$Companion;", "", "()V", "KEY_FIRST_LAUNCH", "", "PREFS_NAME", "instance", "Lcom/wealthguardian/app/WealthGuardianApp;", "isFirstLaunch", "", "setFirstLaunchDone", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isFirstLaunch() {
            return false;
        }
        
        public final void setFirstLaunchDone() {
        }
    }
}