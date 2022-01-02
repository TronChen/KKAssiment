package com.tron.shared.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tron.shared.model.History
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HistoryDao {

    @Query("SELECT COUNT(*) FROM history_table")
    fun count(): Single<Int>

    // Query
    @Query("SELECT * FROM history_table")
    fun getAllHistory(): Single<List<History>>

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History): Completable

    // Update
    @Update
    fun updateHistory(history: History): Completable

    // Delete
    @Query("DELETE FROM history_table WHERE `key` = :date")
    fun deleteHistory(date: Long): Completable

    @Query("DELETE FROM history_table")
    fun clear(): Completable
}
