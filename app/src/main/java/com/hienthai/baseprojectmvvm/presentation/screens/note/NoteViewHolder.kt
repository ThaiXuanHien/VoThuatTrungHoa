package com.hienthai.baseprojectmvvm.presentation.screens.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity
import com.hienthai.baseprojectmvvm.databinding.ItemNoteBinding

class NoteViewHolder(
    private val binding: ItemNoteBinding,
    private val onItemClick: (NoteEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    var item: NoteEntity? = null
        private set

    fun onBind(item: NoteEntity) {
        this.item = item

        binding.run {
            tvTitleItemNote.text = item.title
            tvDateItemNote.text = item.date

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
    companion object {
        fun create(parent: ViewGroup, onItemClick: (NoteEntity) -> Unit) = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let { NoteViewHolder(it, onItemClick) }
    }
}