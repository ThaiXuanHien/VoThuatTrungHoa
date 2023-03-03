package com.hienthai.baseprojectmvvm.presentation.base.viewbinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialog
import androidx.viewbinding.ViewBinding
import com.hienthai.baseprojectmvvm.extensions.getBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BindingDialog<V : ViewBinding> : AppCompatDialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, theme: Int) : super(context, theme)

    private var _binding: V? = null

    protected val dialogScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    val binding: V
        get() = _binding
            ?: throw RuntimeException("Should only use binding after createView")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getBinding(LayoutInflater.from(context), null)
        setContentView(binding.root)
        setOnDismissListener {
            dialogScope.cancel()
        }
    }
}