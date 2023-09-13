package com.hienthai.baseprojectmvvm.presentation.screens.note

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity
import com.hienthai.baseprojectmvvm.databinding.FragmentNoteBinding
import com.hienthai.baseprojectmvvm.presentation.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoteFragment : BaseFragment<FragmentNoteBinding>() {

    private val viewModel: NoteViewModel by viewModel()
    private val adapter by lazy { NoteAdapter() }
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

            viewModel.saveNote(noteText)

            edtInputNote.setText("")
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

        viewModel.noteList.onEach(::renderNotes).launchIn(lifecycleScope)
        viewModel.newDate.onEach(::renderDate).launchIn(lifecycleScope)
    }

}