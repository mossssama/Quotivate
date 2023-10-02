package com.newOs.quotivate.quotes.presentation.main

import com.newOs.quotivate.quotes.domain.entity.Quote

data class MainScreenState(
    val quote: Quote,
    val isLoading: Boolean,
    val error: String? = null
)
