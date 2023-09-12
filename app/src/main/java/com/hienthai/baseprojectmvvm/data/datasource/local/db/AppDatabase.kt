package com.hienthai.baseprojectmvvm.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hienthai.baseprojectmvvm.data.datasource.local.db.dao.UserInfoDAO
import com.hienthai.baseprojectmvvm.data.datasource.local.db.entity.UserInfoEntity


@Database(entities = [UserInfoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDAO
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun newInstance(context: Context): AppDatabase {
            return when (val temp = INSTANCE) {
                null -> synchronized(this) {
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "tb_user_info"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                else -> temp
            }
        }
    }
}