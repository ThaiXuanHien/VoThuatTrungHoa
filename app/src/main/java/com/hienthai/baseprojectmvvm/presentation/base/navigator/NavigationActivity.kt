package com.hienthai.baseprojectmvvm.presentation.base.navigator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hienthai.baseprojectmvvm.R
import com.hienthai.baseprojectmvvm.extensions.hideKeyboard
import com.hienthai.baseprojectmvvm.extensions.makeFullScreen
import com.hienthai.baseprojectmvvm.presentation.BaseFragment
import com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.FragNavController
import com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.FragNavTransactionOptions
import com.hienthai.baseprojectmvvm.presentation.base.viewbinding.BindingActivity

abstract class NavigationActivity<V : ViewBinding> : BindingActivity<V>(), FragNavController.TransactionListener,
    FragNavController.RootFragmentListener {
    open val hasBottomBar = false
    abstract val containerId: Int

    private val navController: FragNavController by lazy {
        FragNavController(
            supportFragmentManager,
            containerId
        )
    }

    val navigator: Navigator by lazy { FragNavigator(navController) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.makeFullScreen()
        navController.apply {
            transactionListener = this@NavigationActivity
            rootFragmentListener = this@NavigationActivity
            defaultTransactionOptions = FragNavTransactionOptions.newBuilder().customAnimations(
                enterAnimation = R.anim.fragment_enter,
                exitAnimation = R.anim.fragment_exit,
                popEnterAnimation = R.anim.fragment_pop_enter,
                popExitAnimation = R.anim.fragment_pop_exit
            ).allowStateLoss(true).build()
            maxLifecycleOnSwitch = true
            fragmentHideStrategy = FragNavController.HIDE
        }

        navController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        navController.onSaveInstanceState(outState)
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        if (fragment?.isAdded == true) (fragment as? BaseFragment<*>)?.onFragmentReturn()
    }

    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        if (transactionType == FragNavController.TransactionType.POP) {
            (fragment as? BaseFragment<*>)?.onFragmentReturn()
        }
        hideKeyboard()
    }

}