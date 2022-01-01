package com.tron.shared.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tron.shared.model.Response
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ResponseDao {

    @Query("SELECT COUNT(*) FROM response_table")
    fun count(): Single<Int>

    // Query
    @Query("SELECT * FROM response_table")
    fun getAllResponse(): Single<List<Response>>

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResponse(response: Response): Completable

    // Update
    @Update
    fun updateResponse(response: Response): Completable

    // Delete
    @Query("DELETE FROM response_table WHERE `key` = :code")
    fun deleteResponse(code: String): Completable

    @Query("DELETE FROM response_table")
    fun clear(): Completable
}
