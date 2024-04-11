package com.hienthai.vohoctrunghoa.presentation.base.navigator.fragnav

interface FragNavPopController {
    @Throws(UnsupportedOperationException::class)
    fun tryPopFragments(popDepth: Int, transactionOptions: FragNavTransactionOptions?): Int
}
