package com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.tabhistory

import android.os.Bundle
import com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.FragNavPopController
import com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.FragNavTransactionOptions


class CurrentTabHistoryController(fragNavPopController: FragNavPopController) : BaseFragNavTabHistoryController(fragNavPopController) {

    @Throws(UnsupportedOperationException::class)
    override fun popFragments(popDepth: Int,
                              transactionOptions: FragNavTransactionOptions?): Boolean {
        return fragNavPopController.tryPopFragments(popDepth, transactionOptions) > 0
    }

    override fun switchTab(index: Int) {}

    override fun onSaveInstanceState(outState: Bundle) {}

    override fun restoreFromBundle(savedInstanceState: Bundle?) {}
}
