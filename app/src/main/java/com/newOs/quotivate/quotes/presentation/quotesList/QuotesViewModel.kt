package com.newOs.quotivate.quotes.presentation.quotesList

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.quotes.domain.useCases.GetInitialQuotesUseCase
import com.newOs.quotivate.quotes.domain.useCases.ToggleFavoriteStateUseCase
import kotlinx.coroutines.*

class QuotesViewModel:ViewModel() {
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
    init{ getQuotes() }

    /* Feed _state with the quotes from db */
    private fun getQuotes(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val receivedQuotes = getInitialQuotesUseCase()
            _state = _state.copy(
                quotes = receivedQuotes,
                isLoading = false,
            )
        }
    }

    /* Toggle quote favorite state value in db in case user clicked on it */
    fun toggleFavoriteState(quoteId: Int,oldValue: Boolean){
        viewModelScope.launch {
            val updatedQuotesList = toggleFavoriteStateUseCase(quoteId,oldValue)
            _state = _state.copy(quotes=updatedQuotesList)
        }
    }

}