package com.hienthai.vohoctrunghoa.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.hienthai.vohoctrunghoa.databinding.FragmentBackActionBarBinding
import com.hienthai.vohoctrunghoa.extensions.navigator
import com.hienthai.vohoctrunghoa.extensions.setSafeClickListener

abstract class BackActionBarFragment<V : ViewBinding> : BaseFragment<V>() {

    override val hasBottomBar: Boolean get() = true

    private lateinit var backBinding: FragmentBackActionBarBinding

    protected fun setTitle(title: CharSequence) {
        backBinding.tvTitle.text = title
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        backBinding = FragmentBackActionBarBinding.inflate(inflater)
        backBinding.fragmentContainer.addView(
            view,
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        return backBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backBinding.btnBack.setSafeClickListener {
            navigator?.pop()
        }
    }
}