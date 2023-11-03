package com.hienthai.baseprojectmvvm.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hienthai.baseprojectmvvm.R
import com.hienthai.baseprojectmvvm.databinding.ActivityMainBinding
import com.hienthai.baseprojectmvvm.presentation.base.navigator.NavigationActivity
import com.hienthai.baseprojectmvvm.presentation.screens.note.NoteFragment

class MainActivity : NavigationActivity<ActivityMainBinding>() {
    override val containerId: Int
        get() = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment {
        return NoteFragment()
    }
}