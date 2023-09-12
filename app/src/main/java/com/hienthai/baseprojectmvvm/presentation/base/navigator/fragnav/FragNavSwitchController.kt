package com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav

interface FragNavSwitchController {
    fun switchTab(@FragNavController.TabIndex index: Int, transactionOptions: FragNavTransactionOptions?)
}
