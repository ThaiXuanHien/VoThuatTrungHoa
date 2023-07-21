package com.hienthai.baseprojectmvvm.data.datasource.remote

import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<String>
}