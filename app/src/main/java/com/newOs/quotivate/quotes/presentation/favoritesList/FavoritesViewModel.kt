package com.newOs.quotivate.quotes.presentation.favoritesList

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.quotes.domain.useCases.favorites.GetInitialFavoritesUseCase
import com.newOs.quotivate.quotes.domain.useCases.favorites.ToggleFavoriteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getInitialFavoritesUseCase: GetInitialFavoritesUseCase,
    private val toggleFavoriteStateUseCase: ToggleFavoriteStateUseCase
):ViewModel() {
    private var _state by mutableStateOf(
        FavoritesScreenState(
            quotes = emptyList(),
            isLoading = true
        )
    )

    /* To Prevent From updating state from UI layer (QuotesScreen) */
    val state: State<FavoritesScreenState>
        get() = derivedStateOf { _state }


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

    /* Feed _state with the quotes once the QuotesViewModel instance is created */
    init{ getFavorites() }

    /* Feed _state with the quotes from db */
    private fun getFavorites(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val receivedQuotes = getInitialFavoritesUseCase()
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