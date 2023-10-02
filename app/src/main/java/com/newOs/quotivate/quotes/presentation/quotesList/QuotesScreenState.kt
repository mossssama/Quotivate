package com.newOs.quotivate.quotes.presentation.quotesList

import com.newOs.quotivate.quotes.domain.entity.Quote

/* */
data class QuotesScreenState(
    val quotes: List<Quote>,   // Stores a list of quotes to be displayed.
    val isLoading: Boolean,    // Indicates whether quotes are currently being loaded.
    val error: String? = null  // Stores an error message if there is an error.
)
