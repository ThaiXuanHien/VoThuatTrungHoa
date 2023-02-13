package com.hienthai.baseprojectmvvm.data.datasource.remote.interceptor

import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RefreshTokenInterceptor : Interceptor {

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
    //private val service: RefreshTokenService by lazy { retrofit.create(RefreshTokenService::class.java) }

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .dispatcher(Dispatcher().apply { maxRequests = 1 })
            .addInterceptor(RefreshTokenHeaderInterceptor())

//        if (BuildConfig.DEBUG) {
//            val logging = HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            }
//            client.addInterceptor(logging)
//        }
        Retrofit.Builder()
            .client(client.build())
            //.baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    private val gsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().setLenient().create()
    )
}