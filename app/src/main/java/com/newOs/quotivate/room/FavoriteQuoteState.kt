package com.newOs.quotivate.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteQuoteState(
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true) val id : Int
)
