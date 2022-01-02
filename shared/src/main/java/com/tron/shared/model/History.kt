package com.tron.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class History(

    @PrimaryKey(autoGenerate = true)
    val key: Int? = null,

    @ColumnInfo(name = "full_short_link")
    val full_short_link: String,

    @ColumnInfo(name = "original_link")
    val original_link: String

)
