package com.newOs.quotivate.quotes.presentation.favoritesList

import com.newOs.quotivate.quotes.domain.Quote

data class FavoritesScreenState(
    val quotes: List<Quote>,
    val isLoading: Boolean,
    val error: String? = null
)
