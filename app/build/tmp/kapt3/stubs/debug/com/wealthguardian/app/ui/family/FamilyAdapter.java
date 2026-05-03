package com.wealthguardian.app.ui.family;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0011\u0012B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/wealthguardian/app/ui/family/FamilyAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/wealthguardian/app/data/model/FamilyMember;", "Lcom/wealthguardian/app/ui/family/FamilyAdapter$VH;", "onEdit", "Lkotlin/Function1;", "", "onRemove", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "h", "i", "", "onCreateViewHolder", "p", "Landroid/view/ViewGroup;", "t", "Diff", "VH", "app_debug"})
public final class FamilyAdapter extends androidx.recyclerview.widget.ListAdapter<com.wealthguardian.app.data.model.FamilyMember, com.wealthguardian.app.ui.family.FamilyAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.wealthguardian.app.data.model.FamilyMember, kotlin.Unit> onEdit = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.wealthguardian.app.data.model.FamilyMember, kotlin.Unit> onRemove = null;
    
    public FamilyAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wealthguardian.app.data.model.FamilyMember, kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wealthguardian.app.data.model.FamilyMember, kotlin.Unit> onRemove) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.wealthguardian.app.ui.family.FamilyAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup p, int t) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.ui.family.FamilyAdapter.VH h, int i) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/wealthguardian/app/ui/family/FamilyAdapter$Diff;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/wealthguardian/app/data/model/FamilyMember;", "()V", "areContentsTheSame", "", "o", "n", "areItemsTheSame", "app_debug"})
    public static final class Diff extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.wealthguardian.app.data.model.FamilyMember> {
        
        public Diff() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.FamilyMember o, @org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.FamilyMember n) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.FamilyMember o, @org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.data.model.FamilyMember n) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wealthguardian/app/ui/family/FamilyAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/wealthguardian/app/databinding/ItemFamilyMemberBinding;", "(Lcom/wealthguardian/app/ui/family/FamilyAdapter;Lcom/wealthguardian/app/databinding/ItemFamilyMemberBinding;)V", "getB", "()Lcom/wealthguardian/app/databinding/ItemFamilyMemberBinding;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.wealthguardian.app.databinding.ItemFamilyMemberBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.wealthguardian.app.databinding.ItemFamilyMemberBinding b) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wealthguardian.app.databinding.ItemFamilyMemberBinding getB() {
            return null;
        }
    }
}