package com.hienthai.baseprojectmvvm.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.UserInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDAO {
    @Insert(onConflict = REPLACE)
    suspend fun insert(userInfo: UserInfoEntity)
    @Query("SELECT * FROM tb_user_info")
    fun getAllUsers(): Flow<List<UserInfoEntity>>
    @Query("delete from tb_user_info where userId = :userId")
    suspend fun deleteById(userId : Int)
}