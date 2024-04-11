package com.hienthai.vohoctrunghoa.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.hienthai.vohoctrunghoa.data.repository.HomeRepository
import com.hienthai.vohoctrunghoa.extensions.stateFlow

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val listVoHoc = stateFlow(mutableListOf<VoHoc>())

    init {
        listVoHoc.value = homeRepository.getListVoHoc()
    }

}