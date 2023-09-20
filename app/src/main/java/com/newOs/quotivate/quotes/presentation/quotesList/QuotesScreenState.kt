package com.newOs.quotivate.quotes.presentation.quotesList

import com.newOs.quotivate.quotes.domain.Quote

data class QuotesScreenState(
    val quotes: List<Quote>,
    val isLoading: Boolean,
    val error: String? = null
)
