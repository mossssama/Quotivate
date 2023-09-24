package com.newOs.quotivate.quotes.presentation.quoteOfDay

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.quotes.domain.Quote
import com.newOs.quotivate.quotes.domain.useCases.GetRandomQuoteUseCase
import com.newOs.quotivate.quotes.domain.useCases.ToggleRandomQuoteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val toggleRandomQuoteStateUseCase: ToggleRandomQuoteStateUseCase
): ViewModel() {
    private var _state by mutableStateOf(
        QuoteScreenState(
            quote = Quote("","",false),
            isLoading = true
        )
    )

    /* To Prevent From updating state from UI layer (QuotesScreen) */
    val state: State<QuoteScreenState>
        get() = derivedStateOf { _state }


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

    init{ getRandomQuote() }

    fun getRandomQuote(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val receivedQuote = getRandomQuoteUseCase()
            _state = _state.copy(
                quote = receivedQuote,
                isLoading = false,
            )
        }
    }

    fun toggleFavoriteState(id: Int) {
        viewModelScope.launch {
            toggleRandomQuoteStateUseCase(id)
        }
    }

}