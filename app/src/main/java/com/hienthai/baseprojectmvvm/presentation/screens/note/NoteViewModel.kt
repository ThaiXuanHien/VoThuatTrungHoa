package com.hienthai.baseprojectmvvm.presentation.screens.note

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity
import com.hienthai.baseprojectmvvm.data.repository.NoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.util.Date

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val noteList = noteRepository.noteList.asLiveDataFlow()

    val newDate = flow {
        while (true) {
            emit(createDate())
            delay(500L)
        }
    }

    fun saveNote(title: String) {
        viewModelScope.launch {
            noteRepository.save(
                createNote(title)
            )
        }
    }

    private fun createNote(title: String) = NoteEntity(
        title = title,
        date = createDate()
    )

    private fun createDate(): String = DateFormat.format("dd/MM/yyyy, hh:mm:ss", Date()).toString()

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)
}