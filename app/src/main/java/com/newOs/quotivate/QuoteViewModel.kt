package com.newOs.quotivate

import androidx.lifecycle.ViewModel

class QuotesViewModel():ViewModel() {
    fun getAllQuotes()= quoteList
}

class QuoteViewModel():ViewModel() {
    fun getRandomQuote()= quoteList.random()
}

class FavoritesViewModel : ViewModel() {
    fun getAllFavorites() = quoteList.filter { it.isFavorite }
}

