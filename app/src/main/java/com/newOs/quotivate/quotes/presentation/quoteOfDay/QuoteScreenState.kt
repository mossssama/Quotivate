package com.newOs.quotivate.quotes.presentation.quoteOfDay

import com.newOs.quotivate.quotes.domain.Quote

data class QuoteScreenState(
    val quote: Quote,
    val isLoading: Boolean,
    val error: String? = null
)
