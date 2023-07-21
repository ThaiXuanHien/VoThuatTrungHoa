package com.hienthai.baseprojectmvvm.data.repository

import com.hienthai.baseprojectmvvm.data.datasource.remote.ApiService

class DataRepository(private val apiService: ApiService) {
    suspend fun getPosts(): List<String> {
        return apiService.getPosts()
    }
}