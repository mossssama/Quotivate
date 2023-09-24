package com.newOs.quotivate.quotes.presentation.quotesList

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.newOs.quotivate.quotes.domain.Quote
import com.newOs.quotivate.quotes.domain.useCases.LoadPagedQuotesUseCase
import com.newOs.quotivate.quotes.domain.useCases.quotes.ToggleQuoteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val toggleFavoriteStateUseCase: ToggleQuoteStateUseCase,
    private val loadPagedQuotesUseCase: LoadPagedQuotesUseCase
):ViewModel() {
    private var _state by mutableStateOf(
        QuotesScreenState(
            quotes = emptyList(),
            isLoading = true
        )
    )

    val state = loadPagedQuotesUseCase()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

    init{ getQuotes() }


    fun toggleFavoriteState(quoteId: Int,oldValue: Boolean){
        viewModelScope.launch {
            val updatedQuotesList = toggleFavoriteStateUseCase(quoteId,oldValue)
            _state = _state.copy(quotes=updatedQuotesList)
        }
    }


    private fun getQuotes() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val quotes = mutableListOf<Quote>()
            loadPagedQuotesUseCase().collect { pagingData ->
                pagingData.map { Quote(text=it.text, author = it.author, id = it.id, isFavorite = false) }
                _state = _state.copy(
                    quotes = quotes,
                    isLoading = false,
                )
            }
        }
    }

}