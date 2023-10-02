package com.newOs.quotivate.quotes.domain.entity

data class Quote(
    val author: String,
    val text: String,
    val isFavorite: Boolean = false,
    val id : Int = 0
)