package com.hienthai.baseprojectmvvm.data.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RefreshTokenHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Content-Type","application/json")
            .header("User-Agent", "android")
        return chain.proceed(request.build())
    }
}