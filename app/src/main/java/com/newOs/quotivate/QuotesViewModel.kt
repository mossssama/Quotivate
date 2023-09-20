package com.newOs.quotivate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.api.RetrofitClient
import com.newOs.quotivate.helpers.convertToQuoteList
import com.newOs.quotivate.room.FavoriteQuoteState
import com.newOs.quotivate.room.Quote
import com.newOs.quotivate.room.QuoteDatabase
import kotlinx.coroutines.*
import kotlin.Exception

class QuotesViewModel():ViewModel() {
    var state by mutableStateOf(
        QuotesScreenState(
            quotes = emptyList(),
            isLoading = true
        )
    )

    private var quotesDao = QuoteDatabase.getInstance(QuotesApplication.getApplicationContext())

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        state = state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

    init{ getAllQuotes() }

    private fun getAllQuotes(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val receivedQuotes = getQuotesFromAPI()
            state = state.copy(
                quotes = receivedQuotes,
                isLoading = false,
            )
        }
    }

    private suspend fun getQuotesFromAPI() = withContext(Dispatchers.IO){
        try {
            updateLocalDatabase()
        }catch (e:Exception){
            if(quotesDao.getAllQuotes().isEmpty()){
                throw Exception("Something went wrong, No data was found, try connecting to the Internet")
            }
        }
        quotesDao.getAllQuotes()
    }

    private suspend fun updateLocalDatabase() {
        val quotes = RetrofitClient.api.getAllQuotes()
        val favoriteQuotesList = quotesDao.getAllFavoriteQuotes()
        quotesDao.addAllQuotes(convertToQuoteList(quotes))
        quotesDao.updateAll(favoriteQuotesList.map { FavoriteQuoteState(id=it.id, isFavorite = true) })
    }

    fun toggleFavoriteState(quoteId: Int){
        val quotes = state.quotes.toMutableList()
        val itemIndex = quotes.indexOfFirst { it.id == quoteId }

        viewModelScope.launch {
            val updatedQuotesList = toggleFavoriteQuote(quoteId,!quotes[itemIndex].isFavorite)
            state = state.copy(
                quotes=updatedQuotesList
            )
        }

    }

    private suspend fun toggleFavoriteQuote(quoteId:Int, newFavoriteState: Boolean) = withContext(Dispatchers.IO){
        quotesDao.updateQuote(FavoriteQuoteState(id = quoteId, isFavorite = newFavoriteState))
        return@withContext quotesDao.getAllQuotes()
    }

    fun getAllFavorites() = quoteList.filter { it.isFavorite }
}