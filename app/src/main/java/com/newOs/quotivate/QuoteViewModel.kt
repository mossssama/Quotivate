package com.newOs.quotivate

import androidx.lifecycle.ViewModel

class QuotesViewModel():ViewModel() {
    fun getQuote()= quoteList
}



class QuoteViewModel():ViewModel() {
    fun getQuote()= quoteList.random()
}

