package com.hienthai.baseprojectmvvm.application

import android.app.Application
import com.hienthai.baseprojectmvvm.BuildConfig
import com.hienthai.baseprojectmvvm.di.module.appModule
import com.hienthai.baseprojectmvvm.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, viewModelModule)
        }
    }
}