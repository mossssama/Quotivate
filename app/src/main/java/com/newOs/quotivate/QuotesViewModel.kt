package com.newOs.quotivate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class QuotesViewModel(
    private val stateHandle: SavedStateHandle
):ViewModel() {
    var state by mutableStateOf(restoreFavoriteQuotes())

    private fun getAllQuotes()= quoteList

    fun toggleFavoriteState(quoteId: Int){
        val quotes = state.toMutableList()
        val itemIndex = quotes.indexOfFirst { it.id == quoteId }
        quotes[itemIndex] = quotes[itemIndex].copy(isFavorite = !quotes[itemIndex].isFavorite)
        storeSelectedQuote(quotes[itemIndex])
        state = quotes
    }

    private fun storeSelectedQuote(quote: QQuote){
        val savedHandleList = stateHandle.get<List<Int>?>(FAV_IDS).orEmpty().toMutableList()
        if(quote.isFavorite) savedHandleList.add(quote.id)
        else savedHandleList.remove(quote.id)
        stateHandle[FAV_IDS] = savedHandleList
    }

    private fun restoreFavoriteQuotes(): List<QQuote>{
        val quotes = getAllQuotes()
        stateHandle.get<List<Int>?>(FAV_IDS)?.let { savedIds ->
            savedIds.forEach { quoteId ->
                quotes.find { it.id == quoteId }?.isFavorite = true
            }
        }
        return quotes
    }

    companion object {
        const val FAV_IDS = "FavoriteQuotesIDs"
    }



















    fun getRandomQuote() = quoteList.random()
    fun getAllFavorites() = quoteList.filter { it.isFavorite }
}