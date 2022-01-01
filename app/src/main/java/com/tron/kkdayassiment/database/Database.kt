package com.tron.kkdayassiment.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tron.shared.dao.ResponseDao
import com.tron.shared.model.Response

@androidx.room.Database(
    entities = [
        Response::class,
    ],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun responseDao(): ResponseDao

    companion object {

        private var instance: Database? = null

        @Synchronized
        fun get(context: Context): Database {
            if (instance == null) {

                instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java, "Room.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}
