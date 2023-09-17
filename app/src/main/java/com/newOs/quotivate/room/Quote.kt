package com.newOs.quotivate.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    val author: String,
    val text: String,
    val isFavorite: Boolean,
    @PrimaryKey(autoGenerate = true) val id : Int =0
)
