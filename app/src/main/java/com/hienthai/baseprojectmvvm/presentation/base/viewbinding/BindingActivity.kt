package com.hienthai.baseprojectmvvm.presentation.base.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hienthai.baseprojectmvvm.extensions.getBinding


open class BindingActivity<V : ViewBinding> : AppCompatActivity() {
    private var _binding: V? = null

    val binding: V
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}