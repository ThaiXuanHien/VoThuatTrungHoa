package com.hienthai.baseprojectmvvm.data.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HttpHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Content-Type","application/json")
            .header("User-Agent", "android")
//            .header("application", APPLICATION_NAME)
//            .header("device-type", "$DEVICE_TYPE_ANDROID")
//        if (AppPrefs.token.isNotEmpty() && !AppPrefs.ignoreToken) {
//            request.header("Authorization", "Bearer ${AppPrefs.token}")
//        }
//        AppPrefs.ignoreToken = false
        return chain.proceed(request.build())
    }
}