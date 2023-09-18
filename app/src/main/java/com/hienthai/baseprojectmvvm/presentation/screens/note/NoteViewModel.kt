package com.hienthai.baseprojectmvvm.presentation.screens.note

import android.provider.ContactsContract.CommonDataKinds.Note
import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity
import com.hienthai.baseprojectmvvm.data.repository.NoteRepository
import com.hienthai.baseprojectmvvm.extensions.sharedEventFlow
import com.hienthai.baseprojectmvvm.extensions.stateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.util.Date

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    private val _noteList = stateFlow(listOf<NoteEntity>())
    val noteList = _noteList.asStateFlow()

    init {
        noteRepository.getAllNote().onEach {
            _noteList.value = it
        }.launchIn(viewModelScope)
    }

    val newDate = flow {
        while (true) {
            emit(createDate())
            delay(500)
        }
    }

    fun saveNote(id: Int?, title: String) {
        viewModelScope.launch {
            if (id != null) {
                val existingNote = _noteList.value.find { it.id == id }
                if (existingNote != null) {
                    val updatedNote = existingNote.copy(title = title, date = createDate())
                    noteRepository.save(updatedNote)
                }
            } else {
                val newNote = NoteEntity(title = title, date = createDate())
                noteRepository.save(newNote)
            }
        }
    }

    private fun createDate(): String = DateFormat.format("dd/MM/yyyy, hh:mm:ss", Date()).toString()

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}