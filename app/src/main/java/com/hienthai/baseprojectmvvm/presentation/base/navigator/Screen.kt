package com.hienthai.baseprojectmvvm.presentation.base.navigator

import androidx.fragment.app.Fragment

interface Screen {
    fun fragment(): Fragment
}

fun screenOf(creator: () -> Fragment): Screen = object : Screen {
    override fun fragment(): Fragment = creator()
}