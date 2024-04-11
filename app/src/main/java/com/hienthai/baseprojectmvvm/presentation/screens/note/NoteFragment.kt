package com.hienthai.baseprojectmvvm.presentation.screens.note

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity
import com.hienthai.baseprojectmvvm.databinding.FragmentNoteBinding
import com.hienthai.baseprojectmvvm.extensions.observe
import com.hienthai.baseprojectmvvm.extensions.setSafeClickListener
import com.hienthai.baseprojectmvvm.extensions.toast
import com.hienthai.baseprojectmvvm.presentation.BaseFragment
import com.hienthai.baseprojectmvvm.presentation.customview.datetime.SingleDateAndTimePicker
import com.hienthai.baseprojectmvvm.presentation.customview.datetime.SingleDateAndTimePickerDialog
import com.hienthai.baseprojectmvvm.utils.ConnectivityObserver
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.Date
import java.util.TimeZone


class NoteFragment : BaseFragment<FragmentNoteBinding>() {

    private val viewModel: NoteViewModel by viewModel()
    private val adapter by lazy { NoteAdapter(::onItemClicked) }
    private var selectedNote: NoteEntity? = null

    override fun initView() {
        super.initView()
        binding.run {
            rcvNotes.layoutManager = LinearLayoutManager(context)
            SwipeHelper(viewModel::deleteNote).attachToRecyclerView(rcvNotes)
            rcvNotes.adapter = adapter
            btnSave.setOnClickListener {
                saveNote()
            }

            tvDate.setSafeClickListener {
//                navigator?.navigate(screen = noteDetailScreen())
            }
        }
    }

    private fun saveNote() {
        binding.run {
            val noteText = edtInputNote.text.toString().takeIf { it.isNotBlank() } ?: return@run
            viewModel.saveNote(selectedNote?.id, noteText)
            edtInputNote.setText("")
            selectedNote = null
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val restoredNote = savedInstanceState.getParcelable("note", NoteEntity::class.java)
            if (restoredNote != null) {
                selectedNote = restoredNote
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("note", selectedNote)
    }

    private fun renderDate(date: String) {
        binding.tvDate.text = date
    }

    private fun renderNotes(notes: List<NoteEntity>) {
        adapter.submitList(notes)
    }

    override fun initData() {
        super.initData()

        viewModel.noteList.observe(viewLifecycleOwner) {
            renderNotes(it)
        }
        viewModel.newDate.onEach(::renderDate).launchIn(lifecycleScope)

        viewModel.networkStatus.observe(viewLifecycleOwner) {
            when (it) {
                ConnectivityObserver.NetworkStatus.Available -> requireContext().toast("Available")
                ConnectivityObserver.NetworkStatus.Losing -> requireContext().toast("Losing")
                ConnectivityObserver.NetworkStatus.Lost -> requireContext().toast("Lost")
                ConnectivityObserver.NetworkStatus.Unavailable -> requireContext().toast("Unavailable")
            }
        }
    }

    private fun onItemClicked(noteEntity: NoteEntity) {
        selectedNote = noteEntity
        binding.edtInputNote.setText(noteEntity.title)
        binding.edtInputNote.setSelection(binding.edtInputNote.text.length)
    }

}