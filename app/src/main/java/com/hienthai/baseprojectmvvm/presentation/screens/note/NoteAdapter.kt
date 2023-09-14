package com.hienthai.baseprojectmvvm.presentation.screens.note

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.NoteEntity

class NoteAdapter(private val onItemClick: (NoteEntity) -> Unit) : ListAdapter<NoteEntity, NoteViewHolder>(NoteDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.onBind(getItem(position))

}