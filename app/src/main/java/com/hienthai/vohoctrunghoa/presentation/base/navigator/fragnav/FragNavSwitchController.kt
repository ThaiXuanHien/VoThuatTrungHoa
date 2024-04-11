package com.hienthai.vohoctrunghoa.presentation.base.navigator.fragnav

interface FragNavSwitchController {
    fun switchTab(@FragNavController.TabIndex index: Int, transactionOptions: FragNavTransactionOptions?)
}
