package com.hienthai.baseprojectmvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hienthai.baseprojectmvvm.BuildConfig
import com.hienthai.baseprojectmvvm.R
import com.hienthai.baseprojectmvvm.databinding.ActivityMainBinding
import com.hienthai.baseprojectmvvm.presentation.base.viewbinding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvHello.text = BuildConfig.APP_NAME

    }
}