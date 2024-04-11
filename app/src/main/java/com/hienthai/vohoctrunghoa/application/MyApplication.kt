package com.hienthai.vohoctrunghoa.application

import android.app.Application
import com.hienthai.vohoctrunghoa.BuildConfig
import com.hienthai.vohoctrunghoa.di.module.appModule
import com.hienthai.vohoctrunghoa.di.module.viewModelModule
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