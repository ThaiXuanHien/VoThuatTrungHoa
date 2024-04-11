package com.hienthai.vohoctrunghoa.data.repository

import com.hienthai.vohoctrunghoa.R
import com.hienthai.vohoctrunghoa.presentation.screens.home.VoHoc

class HomeRepository {
    fun getListVoHoc() = mutableListOf(
        VoHoc(1, "Dịch Cân Kinh 1", R.raw.dichcankinh1),
        VoHoc(2, "Dịch Cân Kinh 2", R.raw.dichcankinh2),
        VoHoc(3, "Tẩy Tủy Kinh", R.raw.taytuykinh),
    )
}