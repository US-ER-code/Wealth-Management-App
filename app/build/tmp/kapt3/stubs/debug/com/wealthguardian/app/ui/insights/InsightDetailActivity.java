package com.wealthguardian.app.ui.insights;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0012\u0010\u000f\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0016\u00a8\u0006\u0015"}, d2 = {"Lcom/wealthguardian/app/ui/insights/InsightDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "addMiniChart", "", "root", "Landroid/widget/LinearLayout;", "type", "Lcom/wealthguardian/app/data/model/InsightType;", "buildDefaultDetailBody", "", "title", "amount", "", "institution", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "Companion", "app_debug"})
public final class InsightDetailActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_TITLE = "title";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_DESCRIPTION = "description";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_TYPE = "type";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_DETAIL_BODY = "detail_body";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_ACTION_LBL = "action_label";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_AMOUNT = "amount";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_INSTITUTION = "institution";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_CATEGORY = "category";
    @org.jetbrains.annotations.NotNull()
    public static final com.wealthguardian.app.ui.insights.InsightDetailActivity.Companion Companion = null;
    
    public InsightDetailActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.lang.String buildDefaultDetailBody(com.wealthguardian.app.data.model.InsightType type, java.lang.String title, double amount, java.lang.String institution) {
        return null;
    }
    
    private final void addMiniChart(android.widget.LinearLayout root, com.wealthguardian.app.data.model.InsightType type) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/wealthguardian/app/ui/insights/InsightDetailActivity$Companion;", "", "()V", "EXTRA_ACTION_LBL", "", "EXTRA_AMOUNT", "EXTRA_CATEGORY", "EXTRA_DESCRIPTION", "EXTRA_DETAIL_BODY", "EXTRA_INSTITUTION", "EXTRA_TITLE", "EXTRA_TYPE", "launch", "", "context", "Landroid/content/Context;", "insight", "Lcom/wealthguardian/app/data/model/Insight;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void launch(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.Insight insight) {
        }
    }
}