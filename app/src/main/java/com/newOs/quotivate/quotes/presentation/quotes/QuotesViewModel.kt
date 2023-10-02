package com.newOs.quotivate.quotes.presentation.quotes

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.newOs.quotivate.quotes.domain.entity.Quote
import com.newOs.quotivate.quotes.domain.useCases.quotes.LoadPagedQuotesUseCase
import com.newOs.quotivate.quotes.domain.useCases.quotes.ToggleQuoteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val toggleFavoriteStateUseCase: ToggleQuoteStateUseCase,
    private val loadPagedQuotesUseCase: LoadPagedQuotesUseCase
):ViewModel() {

    // Hold the current screen state, initially with empty data and loading.
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
            loadPagedQuotesUseCase().flow.collect { pagingData ->
                pagingData.map { Quote(text = it.text, author = it.author, id = it.id, isFavorite = false) }
                _state = _state.copy(
                    quotes = quotes,
                    isLoading = false,
                )
            }
        }
    }

}