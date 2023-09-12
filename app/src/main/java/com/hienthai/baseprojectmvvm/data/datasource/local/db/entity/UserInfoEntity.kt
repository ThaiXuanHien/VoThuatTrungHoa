package com.hienthai.baseprojectmvvm.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_user_info")
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId : Int = 0,
    @ColumnInfo(name = "name")
    val name: String
)