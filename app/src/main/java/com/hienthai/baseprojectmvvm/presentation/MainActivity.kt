package com.hienthai.baseprojectmvvm.presentation


import android.os.Bundle
import com.hienthai.baseprojectmvvm.R

import com.hienthai.baseprojectmvvm.databinding.ActivityMainBinding
import com.hienthai.baseprojectmvvm.presentation.base.viewbinding.BindingActivity
import com.hienthai.baseprojectmvvm.presentation.screens.UserListFragment

class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserListFragment())
                .commitNow()
        }

    }
}