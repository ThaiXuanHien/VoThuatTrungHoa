package com.hienthai.baseprojectmvvm.di.module

import com.hienthai.baseprojectmvvm.data.datasource.remote.RetrofitClient
import com.hienthai.baseprojectmvvm.data.repository.DataRepository
import org.koin.dsl.module

val appModule = module {
    single { RetrofitClient.apiService }
    single { DataRepository(get()) }
}