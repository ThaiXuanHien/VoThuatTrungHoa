package com.hienthai.vohoctrunghoa.presentation.base.viewbinding

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.hienthai.vohoctrunghoa.R
import com.hienthai.vohoctrunghoa.extensions.getBinding
import com.hienthai.vohoctrunghoa.presentation.HideAbleFragment

open class BindingFragment<V : ViewBinding> : HideAbleFragment() {

    private var _binding: V? = null
    private var loadingDialog: Dialog? = null

    val binding: V
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    open fun initView() {

    }

    open fun initData() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideLoading()
        _binding = null
    }

    protected fun showLoading() {
        hideLoading()
        loadingDialog = Dialog(requireContext(), R.style.Theme_BaseProjectMVVM_DialogFullscreen).apply {
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
            show()
        }
    }

    protected fun hideLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}