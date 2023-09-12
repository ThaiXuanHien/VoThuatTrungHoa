package com.hienthai.baseprojectmvvm.data.repository

import android.content.Context
import com.hienthai.baseprojectmvvm.data.datasource.local.db.AppDatabase
import com.hienthai.baseprojectmvvm.data.datasource.local.db.dao.UserInfoDAO
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.UserInfoEntity
import com.hienthai.baseprojectmvvm.data.datasource.remote.ApiService
import kotlinx.coroutines.flow.Flow

class UserInfoRepository(context: Context) {

    private val userInfoDAO: UserInfoDAO

    init {
        userInfoDAO = AppDatabase.newInstance(context).userInfoDao()
    }

    var getAllUsers: Flow<List<UserInfoEntity>> = userInfoDAO.getAllUsers()

    suspend fun insert(userInfo: UserInfoEntity) {
        userInfoDAO.insert(userInfo)
    }

    suspend fun deleteById(userId : Int){
        userInfoDAO.deleteById(userId)
    }
}