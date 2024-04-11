package com.hienthai.vohoctrunghoa.presentation.screens.home

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hienthai.vohoctrunghoa.databinding.FragmentHomeBinding
import com.hienthai.vohoctrunghoa.extensions.navigator
import com.hienthai.vohoctrunghoa.extensions.observe
import com.hienthai.vohoctrunghoa.presentation.BaseFragment
import com.hienthai.vohoctrunghoa.presentation.insets.voHocScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModel()
    private val adapter by lazy { HomeAdapter(::onItemClicked) }

    override fun initView() {
        super.initView()
        binding.run {
            rcvVohoc.layoutManager = LinearLayoutManager(context)
            rcvVohoc.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.HORIZONTAL
                )
            )

            rcvVohoc.adapter = adapter
        }

    }

    override fun initData() {
        super.initData()

        viewModel.listVoHoc.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    private fun onItemClicked(voHoc: VoHoc) {
        navigator?.navigate(voHocScreen(voHoc.name, voHoc.resource))
    }

}