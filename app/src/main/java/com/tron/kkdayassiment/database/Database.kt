package com.tron.kkdayassiment.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tron.shared.dao.HistoryDao
import com.tron.shared.model.History

@androidx.room.Database(
    entities = [
        History::class,
    ],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

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
