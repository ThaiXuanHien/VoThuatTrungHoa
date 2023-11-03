package com.hienthai.baseprojectmvvm.presentation.screens.detail

import android.util.Log
import com.hienthai.baseprojectmvvm.databinding.FragmentNoteDetailBinding
import com.hienthai.baseprojectmvvm.presentation.BackActionBarFragment


class NoteDetailFragment : BackActionBarFragment<FragmentNoteDetailBinding>() {
    override fun initView() {
        super.initView()
        setTitle("Note Detail")
        Log.e("Hien", "initView: ", )
    }

    override fun initData() {
        super.initData()
    }

    override fun handleBackPressed(): Boolean {
        return super.handleBackPressed()
    }
}