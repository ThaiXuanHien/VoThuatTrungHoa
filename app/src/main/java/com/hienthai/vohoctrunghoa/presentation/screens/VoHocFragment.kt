package com.hienthai.vohoctrunghoa.presentation.screens

import android.net.Uri
import androidx.lifecycle.lifecycleScope
import com.hienthai.vohoctrunghoa.databinding.FragmentVohocBinding
import com.hienthai.vohoctrunghoa.extensions.safeArgs
import com.hienthai.vohoctrunghoa.presentation.BackActionBarFragment
import java.io.File


class VoHocFragment : BackActionBarFragment<FragmentVohocBinding>() {
    private var name : String by safeArgs("name")
    private var resource : Int by safeArgs("resource")
    override fun initView() {
        super.initView()

        setTitle(name)

        val url = Uri.parse("android.resource://${context?.packageName}/${resource}")
        val file = File(url.toString())

        binding.pdfViewer.initWithUrl(
            url = url.toString(),
            lifecycleCoroutineScope = lifecycleScope,
            lifecycle = lifecycle
        )

    }

    override fun initData() {
        super.initData()
    }

    companion object {
        fun newInstance(name: String, resource: Int) = VoHocFragment().apply {
            this.name = name
            this.resource = resource
        }
    }

}