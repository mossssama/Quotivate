package com.newOs.quotivate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuotesViewModel():ViewModel() {
    private fun getAllQuotes()= quoteList

    var state by mutableStateOf(getAllQuotes())

    fun toggleFavoriteState(quoteId: Int){
        val quotes = state.toMutableList()
        val itemIndex = quotes.indexOfFirst { it.id == quoteId }
        quotes[itemIndex] = quotes[itemIndex].copy(isFavorite = !quotes[itemIndex].isFavorite)
        state = quotes
    }


    fun getRandomQuote() = quoteList.random()
    fun getAllFavorites() = quoteList.filter { it.isFavorite }
}