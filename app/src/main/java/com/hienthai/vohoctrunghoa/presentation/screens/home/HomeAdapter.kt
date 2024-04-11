package com.hienthai.vohoctrunghoa.presentation.screens.home

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class HomeAdapter(private val onItemClick: (VoHoc) -> Unit) : ListAdapter<VoHoc, HomeViewHolder>(VoHocDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.onBind(getItem(position))

}