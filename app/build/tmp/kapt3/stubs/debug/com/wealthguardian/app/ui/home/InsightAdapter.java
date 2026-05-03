package com.wealthguardian.app.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0011\u0012B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00072\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/wealthguardian/app/ui/home/InsightAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/wealthguardian/app/data/model/Insight;", "Lcom/wealthguardian/app/ui/home/InsightAdapter$VH;", "onSnooze", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "h", "i", "", "onCreateViewHolder", "p", "Landroid/view/ViewGroup;", "t", "Diff", "VH", "app_debug"})
public final class InsightAdapter extends androidx.recyclerview.widget.ListAdapter<com.wealthguardian.app.data.model.Insight, com.wealthguardian.app.ui.home.InsightAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onSnooze = null;
    
    public InsightAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSnooze) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.wealthguardian.app.ui.home.InsightAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup p, int t) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.ui.home.InsightAdapter.VH h, int i) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/wealthguardian/app/ui/home/InsightAdapter$Diff;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/wealthguardian/app/data/model/Insight;", "()V", "areContentsTheSame", "", "o", "n", "areItemsTheSame", "app_debug"})
    public static final class Diff extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.wealthguardian.app.data.model.Insight> {
        
        public Diff() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.Insight o, @org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.Insight n) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.Insight o, @org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.Insight n) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wealthguardian/app/ui/home/InsightAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/wealthguardian/app/databinding/ItemInsightBinding;", "(Lcom/wealthguardian/app/ui/home/InsightAdapter;Lcom/wealthguardian/app/databinding/ItemInsightBinding;)V", "getB", "()Lcom/wealthguardian/app/databinding/ItemInsightBinding;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.wealthguardian.app.databinding.ItemInsightBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.databinding.ItemInsightBinding b) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wealthguardian.app.databinding.ItemInsightBinding getB() {
            return null;
        }
    }
}