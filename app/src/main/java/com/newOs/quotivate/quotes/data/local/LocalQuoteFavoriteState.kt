package com.newOs.quotivate.quotes.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalQuoteFavoriteState(
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true) val id : Int
)
