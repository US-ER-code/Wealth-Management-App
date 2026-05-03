package com.wealthguardian.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wealthguardian.app.data.model.Goal

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals ORDER BY createdAt DESC")
    fun getAllLive(): LiveData<List<Goal>>

    @Query("SELECT * FROM goals ORDER BY createdAt DESC")
    suspend fun getAll(): List<Goal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(goal: Goal)

    @Update
    suspend fun update(goal: Goal)

    @Delete
    suspend fun delete(goal: Goal)

    @Query("UPDATE goals SET currentAmount = currentAmount + :amount WHERE id = :id")
    suspend fun addLumpsum(id: String, amount: Double)

    @Query("UPDATE goals SET sipAmount = :amount, sipDay = :day WHERE id = :id")
    suspend fun updateSip(id: String, amount: Double, day: Int)

    @Query("SELECT * FROM goals WHERE id = :id")
    suspend fun getById(id: String): Goal?
}
