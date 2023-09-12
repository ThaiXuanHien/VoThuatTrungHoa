package com.hienthai.baseprojectmvvm.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.updatePadding
import androidx.viewbinding.ViewBinding
import com.hienthai.baseprojectmvvm.R
import com.hienthai.baseprojectmvvm.presentation.base.navigator.NavigationActivity
import com.hienthai.baseprojectmvvm.presentation.base.viewbinding.BindingFragment
import timber.log.Timber

open class BaseFragment<V : ViewBinding> : BindingFragment<V>() {
    open val hasBottomBar: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if (hasBottomBar && (activity as? NavigationActivity<*>)?.hasBottomBar == true) {
            val frame = FrameLayout(requireContext())
            frame.addView(view, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
            frame.updatePadding(bottom = resources.getDimensionPixelSize(R.dimen.dp54))
            return frame
        }

        return view
    }

    open fun handleBackPressed(): Boolean {
        return false
    }

    open fun handleErrorCode(code: Int): Boolean {
        return false
    }

    open fun onFragmentReturn() {
        Timber.d("onFragmentReturn: $this")
    }
}