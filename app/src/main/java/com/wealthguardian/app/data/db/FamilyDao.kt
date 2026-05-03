package com.wealthguardian.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wealthguardian.app.data.model.FamilyMember

@Dao
interface FamilyDao {
    @Query("SELECT * FROM family_members ORDER BY inviteSentAt DESC")
    fun getAllLive(): LiveData<List<FamilyMember>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: FamilyMember)

    @Update
    suspend fun update(member: FamilyMember)

    @Delete
    suspend fun delete(member: FamilyMember)

    @Query("DELETE FROM family_members WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("UPDATE family_members SET role = :role, allowedSections = :sections WHERE id = :id")
    suspend fun updateRole(id: String, role: com.wealthguardian.app.data.model.FamilyRole, sections: String)
}
