package com.newOs.quotivate.quotes.domain

data class Quote(
    val author: String,
    val text: String,
    val isFavorite: Boolean = false,
    val id : Int = 0
)