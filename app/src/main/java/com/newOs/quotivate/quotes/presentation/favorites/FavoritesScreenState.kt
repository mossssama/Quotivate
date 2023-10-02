package com.newOs.quotivate.quotes.presentation.favorites

import com.newOs.quotivate.quotes.domain.entity.Quote

// Class represents the Favorites screen state
data class FavoritesScreenState(
    val quotes: List<Quote>,
    val isLoading: Boolean,
    val error: String? = null
)
