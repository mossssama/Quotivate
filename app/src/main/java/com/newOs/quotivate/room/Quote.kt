package com.newOs.quotivate.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    val author: String,
    val text: String,

    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,

    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true) val id : Int = 0
)
