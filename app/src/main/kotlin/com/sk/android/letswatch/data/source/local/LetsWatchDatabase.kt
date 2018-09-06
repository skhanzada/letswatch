package com.sk.android.letswatch.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.sk.android.letswatch.data.Movie

const val DATABASE_NAME = "movies_db"

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class LetsWatchDatabase : RoomDatabase() {

    abstract fun moveDao(): MovieDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: LetsWatchDatabase? = null

        fun getInstance(context: Context): LetsWatchDatabase {
            return instance
                ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LetsWatchDatabase {
            return Room.databaseBuilder(context, LetsWatchDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }
}