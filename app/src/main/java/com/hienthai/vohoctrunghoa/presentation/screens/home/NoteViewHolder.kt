package com.hienthai.vohoctrunghoa.presentation.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hienthai.vohoctrunghoa.databinding.ItemVohocBinding

class HomeViewHolder(
    private val binding: ItemVohocBinding,
    private val onItemClick: (VoHoc) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    var item: VoHoc? = null
        private set

    fun onBind(item: VoHoc) {
        this.item = item

        binding.run {
            tvName.text = item.name

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
    companion object {
        fun create(parent: ViewGroup, onItemClick: (VoHoc) -> Unit) = ItemVohocBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let { HomeViewHolder(it, onItemClick) }
    }
}