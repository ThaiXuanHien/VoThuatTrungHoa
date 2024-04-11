package com.hienthai.vohoctrunghoa.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.hienthai.vohoctrunghoa.data.datasource.local.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Insert(onConflict = REPLACE)
    suspend fun save(note: NoteEntity)
    @Query("SELECT * FROM tb_notes")
    fun getAllNote(): Flow<List<NoteEntity>>
    @Delete
    suspend fun deleteNote(note: NoteEntity)
}