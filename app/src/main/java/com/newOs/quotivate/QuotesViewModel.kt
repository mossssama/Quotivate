package com.newOs.quotivate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.api.RetrofitClient
import com.newOs.quotivate.room.Quote
import kotlinx.coroutines.*

class QuotesViewModel(private val stateHandle: SavedStateHandle):ViewModel() {
    var state by mutableStateOf(emptyList<Quote>())

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        /* Execute any code we want incase no internet connection or server is failed*/
    }

    init{ getAllQuotes() }

    private fun getAllQuotes(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val quotes = getQuotesFromAPI()
            state = convertToQuoteList(quotes).restoreFavoriteQuotes()
        }
    }

    private suspend fun getQuotesFromAPI() = withContext(Dispatchers.IO){ RetrofitClient.api.getAllQuotes() }

    fun toggleFavoriteState(quoteId: Int){
        val quotes = state.toMutableList()
        val itemIndex = quotes.indexOfFirst { it.id == quoteId }
        quotes[itemIndex] = quotes[itemIndex].copy(isFavorite = !quotes[itemIndex].isFavorite)
        storeSelectedQuote(quotes[itemIndex])
        state = quotes
    }

    private fun storeSelectedQuote(quote: Quote){
        val savedHandleList = stateHandle.get<List<Int>?>(FAV_IDS).orEmpty().toMutableList()
        if(quote.isFavorite) savedHandleList.add(quote.id)
        else savedHandleList.remove(quote.id)
        stateHandle[FAV_IDS] = savedHandleList
    }

    private fun List<Quote>.restoreFavoriteQuotes(): List<Quote>{
        stateHandle.get<List<Int>?>(FAV_IDS)?.let { savedIds ->
            savedIds.forEach { quoteId ->
                this.find { it.id == quoteId }?.isFavorite = true
            }
        }
        return this
    }

    companion object {
        const val FAV_IDS = "FavoriteQuotesIDs"
    }


//    fun getRandomQuote() = quoteList.random()
    fun getAllFavorites() = quoteList.filter { it.isFavorite }
}