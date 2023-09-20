package com.newOs.quotivate.quotes.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.quoteList
import com.newOs.quotivate.quotes.QuotesScreenState
import com.newOs.quotivate.quotes.useCase.GetInitialQuotesUseCase
import com.newOs.quotivate.quotes.useCase.ToggleFavoriteStateUseCase
import kotlinx.coroutines.*

class QuotesViewModel():ViewModel() {
    private var _state by mutableStateOf(
        QuotesScreenState(
            quotes = emptyList(),
            isLoading = true
        )
    )

    /* To Prevent From updating state from UI layer (QuotesScreen) */
    val state: State<QuotesScreenState>
        get() = derivedStateOf { _state }


    /* Use Cases linking between ViewModel & Repo */
    private val getInitialQuotesUseCase = GetInitialQuotesUseCase()
    private val toggleFavoriteStateUseCase = ToggleFavoriteStateUseCase()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

    /* Feed _state with the quotes once the QuotesViewModel instance is created */
    init{ getAllQuotes() }

    /* Feed _state with the quotes from db */
    private fun getAllQuotes(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val receivedQuotes = getInitialQuotesUseCase()
            _state = _state.copy(
                quotes = receivedQuotes,
                isLoading = false,
            )
        }
    }

    /* Toggle quote favorite state value in db in case user clicked on it */
    fun toggleFavoriteState(quoteId: Int){
        val quotes = _state.quotes.toMutableList()
        val itemIndex = quotes.indexOfFirst { it.id == quoteId }

        viewModelScope.launch {
            val updatedQuotesList = toggleFavoriteStateUseCase(quoteId,quotes[itemIndex].isFavorite)
            _state = _state.copy(
                quotes=updatedQuotesList
            )
        }
    }




    fun getAllFavorites() = quoteList.filter { it.isFavorite }
}