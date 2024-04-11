package com.hienthai.vohoctrunghoa.presentation.base.navigator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import java.util.*

interface Navigator {

    fun switchTab(tabIndex: Int, animate: Boolean = true)

    fun navigate(screen: Screen, animate: Boolean = true)

    fun pop(animate: Boolean = true)

    fun pops(depth: Int, animate: Boolean = true)

    fun getStack() : Stack<Fragment>?

    fun clearStack(animate: Boolean = true)

    fun sendResult(requestKey: String, result: Bundle)

    fun setResultListener(
        requestKey: String,
        lifecycleOwner: LifecycleOwner,
        listener: (requestKey: String, result: Bundle) -> Unit
    )

    val currentFragment: Fragment?

    val isRoot: Boolean

    val currentTabIndex: Int
}