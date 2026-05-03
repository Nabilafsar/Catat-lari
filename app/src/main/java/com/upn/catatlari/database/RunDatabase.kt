package com.upn.catatlari.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.upn.catatlari.data.RunDao
import com.upn.catatlari.model.RunEntity

@Database(entities = [RunEntity::class], version = 1, exportSchema = false)
abstract class RunDatabase : RoomDatabase() {

    abstract fun runDao(): RunDao

    companion object {
        @Volatile
        private var INSTANCE: RunDatabase? = null

        fun getDatabase(context: Context): RunDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RunDatabase::class.java,
                    "run_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
