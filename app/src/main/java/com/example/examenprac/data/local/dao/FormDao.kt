package com.example.examenprac.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examenprac.data.local.entity.FormEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForm(form: FormEntry)

    @Query("SELECT * FROM form_entries ORDER BY id DESC")
    fun getAllForms(): Flow<List<FormEntry>>

    @Query("DELETE FROM form_entries WHERE id = :id")
    suspend fun deleteById(id: Int)
}
