package com.hienthai.baseprojectmvvm.presentation.screens.note

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity
import com.hienthai.baseprojectmvvm.databinding.FragmentNoteBinding
import com.hienthai.baseprojectmvvm.extensions.observe
import com.hienthai.baseprojectmvvm.presentation.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


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
    }

    private fun onItemClicked(noteEntity: NoteEntity) {
        selectedNote = noteEntity
        binding.edtInputNote.setText(noteEntity.title)
    }

}