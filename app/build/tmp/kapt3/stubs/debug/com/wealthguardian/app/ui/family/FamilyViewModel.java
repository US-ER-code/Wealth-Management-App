package com.wealthguardian.app.ui.family;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J4\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014J\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\bJ$\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/wealthguardian/app/ui/family/FamilyViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/wealthguardian/app/data/db/AppDatabase;", "(Lcom/wealthguardian/app/data/db/AppDatabase;)V", "members", "Landroidx/lifecycle/LiveData;", "", "Lcom/wealthguardian/app/data/model/FamilyMember;", "getMembers", "()Landroidx/lifecycle/LiveData;", "addMember", "", "name", "", "email", "phone", "role", "Lcom/wealthguardian/app/data/model/FamilyRole;", "sections", "", "removeMember", "member", "updateRole", "app_debug"})
public final class FamilyViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.wealthguardian.app.data.db.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.FamilyMember>> members = null;
    
    public FamilyViewModel(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.db.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.FamilyMember>> getMembers() {
        return null;
    }
    
    public final void addMember(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.FamilyRole role, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> sections) {
    }
    
    public final void updateRole(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.FamilyMember member, @org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.FamilyRole role, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> sections) {
    }
    
    public final void removeMember(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.FamilyMember member) {
    }
}