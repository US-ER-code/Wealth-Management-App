package com.wealthguardian.app.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/wealthguardian/app/ui/home/TimelineAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/wealthguardian/app/data/model/TimelineItem;", "Lcom/wealthguardian/app/ui/home/TimelineAdapter$VH;", "()V", "onBindViewHolder", "", "h", "i", "", "onCreateViewHolder", "p", "Landroid/view/ViewGroup;", "t", "Diff", "VH", "app_debug"})
public final class TimelineAdapter extends androidx.recyclerview.widget.ListAdapter<com.wealthguardian.app.data.model.TimelineItem, com.wealthguardian.app.ui.home.TimelineAdapter.VH> {
    
    public TimelineAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.wealthguardian.app.ui.home.TimelineAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup p, int t) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.ui.home.TimelineAdapter.VH h, int i) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/wealthguardian/app/ui/home/TimelineAdapter$Diff;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/wealthguardian/app/data/model/TimelineItem;", "()V", "areContentsTheSame", "", "o", "n", "areItemsTheSame", "app_debug"})
    public static final class Diff extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.wealthguardian.app.data.model.TimelineItem> {
        
        public Diff() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.TimelineItem o, @org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.TimelineItem n) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.TimelineItem o, @org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.TimelineItem n) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wealthguardian/app/ui/home/TimelineAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/wealthguardian/app/databinding/ItemTimelineBinding;", "(Lcom/wealthguardian/app/ui/home/TimelineAdapter;Lcom/wealthguardian/app/databinding/ItemTimelineBinding;)V", "getB", "()Lcom/wealthguardian/app/databinding/ItemTimelineBinding;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.wealthguardian.app.databinding.ItemTimelineBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.databinding.ItemTimelineBinding b) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wealthguardian.app.databinding.ItemTimelineBinding getB() {
            return null;
        }
    }
}