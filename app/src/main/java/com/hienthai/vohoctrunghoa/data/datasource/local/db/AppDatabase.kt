package com.hienthai.vohoctrunghoa.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hienthai.vohoctrunghoa.data.datasource.local.db.dao.NoteDAO
import com.hienthai.vohoctrunghoa.data.datasource.local.db.entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDAO
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun newInstance(context: Context): AppDatabase {
            return when (val temp = INSTANCE) {
                null -> synchronized(this) {
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "tb_note"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                else -> temp
            }
        }
    }
}