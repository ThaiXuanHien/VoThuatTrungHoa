package com.hienthai.vohoctrunghoa.presentation.screens.home

import androidx.recyclerview.widget.DiffUtil

class VoHocDiffCallback : DiffUtil.ItemCallback<VoHoc>() {
    override fun areItemsTheSame(oldItem: VoHoc, newItem: VoHoc): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: VoHoc, newItem: VoHoc): Boolean = oldItem == newItem

}