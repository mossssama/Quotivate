package com.newOs.quotivate

import com.newOs.quotivate.room.Quote

data class QuotesScreenState(
    val quotes: List<Quote>,
    val isLoading: Boolean,
    val error: String? = null
)
