package com.hienthai.vohoctrunghoa.presentation.base.navigator.fragnav.tabhistory

import com.hienthai.vohoctrunghoa.presentation.base.navigator.fragnav.FragNavSwitchController

sealed class NavigationStrategy

object CurrentTabStrategy : NavigationStrategy()

class UnlimitedTabHistoryStrategy(val fragNavSwitchController: FragNavSwitchController) : NavigationStrategy()

class UniqueTabHistoryStrategy(val fragNavSwitchController: FragNavSwitchController) : NavigationStrategy()