package com.hienthai.baseprojectmvvm.presentation.screens.note

import androidx.recyclerview.widget.DiffUtil
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity

class NoteDiffCallback : DiffUtil.ItemCallback<NoteEntity>() {
    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean = oldItem == newItem

}