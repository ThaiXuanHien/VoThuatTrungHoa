package com.hienthai.vohoctrunghoa.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hienthai.vohoctrunghoa.R
import com.hienthai.vohoctrunghoa.databinding.ActivityMainBinding
import com.hienthai.vohoctrunghoa.presentation.base.navigator.NavigationActivity
import com.hienthai.vohoctrunghoa.presentation.screens.home.HomeFragment

class MainActivity : NavigationActivity<ActivityMainBinding>() {
    override val containerId: Int
        get() = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment {
        return HomeFragment()
    }
}