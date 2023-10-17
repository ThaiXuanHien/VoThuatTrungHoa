package com.hienthai.baseprojectmvvm.di.module

import com.hienthai.baseprojectmvvm.data.datasource.remote.RetrofitClient
import com.hienthai.baseprojectmvvm.data.repository.NoteRepository
import com.hienthai.baseprojectmvvm.presentation.screens.note.NoteViewModel
import com.hienthai.baseprojectmvvm.utils.NetworkStatusTracker
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitClient.apiService }
    single { NoteRepository(get()) }
    single { NetworkStatusTracker(get()) }
}
val viewModelModule = module {
    viewModel { NoteViewModel(get(), get()) }
}