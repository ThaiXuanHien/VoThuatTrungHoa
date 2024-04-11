package com.hienthai.vohoctrunghoa.presentation.screens

import android.net.Uri
import androidx.lifecycle.lifecycleScope
import com.hienthai.vohoctrunghoa.databinding.FragmentVohocBinding
import com.hienthai.vohoctrunghoa.extensions.safeArgs
import com.hienthai.vohoctrunghoa.presentation.BackActionBarFragment
import java.io.File


class VoHocFragment : BackActionBarFragment<FragmentVohocBinding>() {
    private var name : String by safeArgs("name")
    private var resource : String by safeArgs("resource")
    override fun initView() {
        super.initView()

        setTitle(name)

        binding.pdfView.fromAsset(resource).load()

    }

    override fun initData() {
        super.initData()
    }

    companion object {
        fun newInstance(name: String, resource: String) = VoHocFragment().apply {
            this.name = name
            this.resource = resource
        }
    }

}