package com.wealthguardian.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wealthguardian.app.data.model.DocumentCategory
import com.wealthguardian.app.data.model.UploadStatus
import com.wealthguardian.app.data.model.VaultDocument

@Dao
interface VaultDao {
    @Query("SELECT * FROM vault_documents ORDER BY uploadedAt DESC, dateAdded DESC")
    fun getAllLive(): LiveData<List<VaultDocument>>

    @Query("SELECT * FROM vault_documents WHERE category = :cat ORDER BY dateAdded DESC")
    fun getByCategory(cat: DocumentCategory): LiveData<List<VaultDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(doc: VaultDocument)

    @Update
    suspend fun update(doc: VaultDocument)

    @Delete
    suspend fun delete(doc: VaultDocument)

    @Query("UPDATE vault_documents SET uploadStatus = :status WHERE id = :id")
    suspend fun updateStatus(id: String, status: UploadStatus)

    @Query("SELECT * FROM vault_documents WHERE id = :id")
    suspend fun getById(id: String): VaultDocument?

    @Query("SELECT COUNT(*) FROM vault_documents WHERE isVerified = 1")
    suspend fun verifiedCount(): Int
}
