package com.hienthai.baseprojectmvvm.data.repository

import android.content.Context
import com.hienthai.baseprojectmvvm.data.datasource.local.db.AppDatabase
import com.hienthai.baseprojectmvvm.data.datasource.local.db.dao.NoteDAO
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity

class NoteRepository(context: Context) {

    private val noteDAO: NoteDAO

    init {
        noteDAO = AppDatabase.newInstance(context).noteDao()
    }

    fun getAllNote() = noteDAO.getAllNote()

    suspend fun save(note: NoteEntity) {
        noteDAO.save(note)
    }

    suspend fun deleteNote(note: NoteEntity){
        noteDAO.deleteNote(note)
    }
}