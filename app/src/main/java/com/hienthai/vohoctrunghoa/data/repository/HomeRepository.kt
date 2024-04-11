package com.hienthai.vohoctrunghoa.data.repository

import com.hienthai.vohoctrunghoa.presentation.screens.home.VoHoc

class HomeRepository {
    fun getListVoHoc() = mutableListOf(
        VoHoc(1, "Dịch Cân Kinh 1", "dichcankinh1.pdf"),
        VoHoc(2, "Dịch Cân Kinh 2", "dichcankinh2.pdf"),
        VoHoc(3, "Tẩy Tủy Kinh", "taytuykinh.pdf")
    )
}