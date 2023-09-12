package com.hienthai.baseprojectmvvm.presentation.base.navigator

import FragNavController
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

import java.util.*

class FragNavigator(private val fragNavController: FragNavController) : Navigator {

    override fun switchTab(tabIndex: Int, animate: Boolean) {
        if (animate)
            fragNavController.switchTab(tabIndex)
        else fragNavController.switchTab(tabIndex, null)
    }

    override fun navigate(screen: Screen, animate: Boolean) {
        if (animate)
            fragNavController.pushFragment(screen.fragment())
        else fragNavController.pushFragment(screen.fragment(), null)
    }

    override fun pop(animate: Boolean) {
        if (animate)
            fragNavController.popFragment()
        else fragNavController.popFragment(null)
    }

    override fun pops(depth: Int, animate: Boolean) {
        if (animate)
            fragNavController.popFragments(depth)
        else fragNavController.popFragments(depth, null)
    }

    override fun getStack(): Stack<Fragment>? {
        return fragNavController.getStack(fragNavController.currentStackIndex)
    }

    override fun clearStack(animate: Boolean) {
        if (animate)
            fragNavController.clearStack()
        else fragNavController.clearStack(null)
    }

    override fun sendResult(requestKey: String, result: Bundle) {
        fragNavController.fragmentManger.setFragmentResult(requestKey, result)
    }

    override fun setResultListener(
        requestKey: String,
        lifecycleOwner: LifecycleOwner,
        listener: (requestKey: String, result: Bundle) -> Unit
    ) {
        fragNavController.fragmentManger.setFragmentResultListener(requestKey, lifecycleOwner, listener)
    }

    override val currentFragment: Fragment?
        get() = fragNavController.currentFrag

    override val isRoot: Boolean
        get() = fragNavController.isRootFragment

    override val currentTabIndex: Int
        get() = fragNavController.currentStackIndex
}