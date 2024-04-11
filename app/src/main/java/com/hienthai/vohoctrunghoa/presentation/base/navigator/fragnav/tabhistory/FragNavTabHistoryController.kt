package com.hienthai.vohoctrunghoa.presentation.base.navigator.fragnav.tabhistory

import android.os.Bundle
import androidx.annotation.IntDef
import com.hienthai.vohoctrunghoa.presentation.base.navigator.fragnav.FragNavTransactionOptions


interface FragNavTabHistoryController {
    /**
     * Define what happens when we try to pop on a tab where root fragment is at the top
     */
    @IntDef(UNIQUE_TAB_HISTORY, UNLIMITED_TAB_HISTORY)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class PopStrategy

    fun popFragments(popDepth: Int, transactionOptions: FragNavTransactionOptions?): Boolean

    fun switchTab(index: Int)

    fun onSaveInstanceState(outState: Bundle)

    fun restoreFromBundle(savedInstanceState: Bundle?)

    companion object {

        /**
         * We keep a history of tabs (each tab is present only once) and we switch to previous tab in history when we pop on root fragment
         */
        const val UNIQUE_TAB_HISTORY = 1

        /**
         * We keep an uncapped history of tabs and we switch to previous tab in history when we pop on root fragment
         */
        const val UNLIMITED_TAB_HISTORY = 2
    }
}



