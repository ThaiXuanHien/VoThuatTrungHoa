package com.hienthai.vohoctrunghoa.presentation.insets

import com.hienthai.vohoctrunghoa.presentation.base.navigator.screenOf
import com.hienthai.vohoctrunghoa.presentation.screens.VoHocFragment


fun voHocScreen(name: String, resource: String) = screenOf { VoHocFragment.newInstance(name, resource) }