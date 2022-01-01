package com.tron.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "response_table")
data class Response(

    @PrimaryKey(autoGenerate = true)
    val key: Int,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "short_link")
    val short_link: String,

    @ColumnInfo(name = "full_short_link")
    val full_short_link: String,

    @ColumnInfo(name = "short_link2")
    val short_link2: String,

    @ColumnInfo(name = "full_short_link2")
    val full_short_link2: String,

    @ColumnInfo(name = "share_linkL")
    val share_linkL: String,

    @ColumnInfo(name = "full_share_link")
    val full_share_link: String,

    @ColumnInfo(name = "original_link")
    val original_link: String
)
