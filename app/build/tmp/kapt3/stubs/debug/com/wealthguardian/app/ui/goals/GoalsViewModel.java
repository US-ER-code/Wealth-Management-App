package com.wealthguardian.app.ui.goals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J6\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0011J\u000e\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\bJ\u001e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/wealthguardian/app/ui/goals/GoalsViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/wealthguardian/app/data/db/AppDatabase;", "(Lcom/wealthguardian/app/data/db/AppDatabase;)V", "goals", "Landroidx/lifecycle/LiveData;", "", "Lcom/wealthguardian/app/data/model/Goal;", "getGoals", "()Landroidx/lifecycle/LiveData;", "addGoal", "", "name", "", "emoji", "target", "", "category", "sipAmount", "sipDay", "", "addLumpsum", "goal", "amount", "deleteGoal", "updateSip", "day", "app_debug"})
public final class GoalsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.wealthguardian.app.data.db.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Goal>> goals = null;
    
    public GoalsViewModel(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.db.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.wealthguardian.app.data.model.Goal>> getGoals() {
        return null;
    }
    
    public final void addGoal(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String emoji, double target, @org.jetbrains.annotations.NotNull()
    java.lang.String category, double sipAmount, int sipDay) {
    }
    
    public final void addLumpsum(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.Goal goal, double amount) {
    }
    
    public final void updateSip(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.Goal goal, double amount, int day) {
    }
    
    public final void deleteGoal(@org.jetbrains.annotations.NotNull()
    com.wealthguardian.app.data.model.Goal goal) {
    }
}