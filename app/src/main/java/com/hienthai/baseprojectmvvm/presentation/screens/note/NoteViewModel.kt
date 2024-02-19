package com.hienthai.baseprojectmvvm.presentation.screens.note

import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity
import com.hienthai.baseprojectmvvm.data.repository.NoteRepository
import com.hienthai.baseprojectmvvm.extensions.sharedEventFlow
import com.hienthai.baseprojectmvvm.extensions.stateFlow
import com.hienthai.baseprojectmvvm.utils.ConnectivityObserver
import com.hienthai.baseprojectmvvm.utils.NetworkStatusTracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date

class NoteViewModel(
    private val noteRepository: NoteRepository,
    private val networkStatusTracker: NetworkStatusTracker
) : ViewModel() {

    private val _noteList = stateFlow(listOf<NoteEntity>())
    val noteList = _noteList.asStateFlow()
    val networkStatus = sharedEventFlow<ConnectivityObserver.NetworkStatus>()
    init {
        noteRepository.getAllNote().onEach {
            Log.e("Hien", "noteRepository: Hien3")
            _noteList.value = it

        }.launchIn(viewModelScope)

        networkStatusTracker.observe().onEach {
            networkStatus.tryEmit(it)
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