package com.hienthai.vohoctrunghoa.di.module

import com.hienthai.vohoctrunghoa.data.datasource.remote.RetrofitClient
import com.hienthai.vohoctrunghoa.data.repository.HomeRepository
import com.hienthai.vohoctrunghoa.presentation.screens.home.HomeViewModel
import com.hienthai.vohoctrunghoa.utils.NetworkStatusTracker
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitClient.apiService }
    single { HomeRepository() }
    single { NetworkStatusTracker(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}