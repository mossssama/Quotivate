package com.newOs.quotivate.quotes.presentation.favorites

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

    // Mutable State to hold the current state of the Favorites screen
    private var _state by mutableStateOf(
        FavoritesScreenState(
            quotes = emptyList(),
            isLoading = true
        )
    )
    // Expose an Immutable state to prevent direct modification from the UI
    val state: State<FavoritesScreenState>
        get() = derivedStateOf { _state }


    // Coroutine handler to handle errors during data loading
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

    // Initialize the state by fetching initial favorites
    init{ getFavorites() }

    // Fetch initial favorite quotes from a data source
    private fun getFavorites(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val receivedQuotes = getInitialFavoritesUseCase()
            _state = _state.copy(
                quotes = receivedQuotes,
                isLoading = false,
                error = ""
            )
        }
    }

    // Toggle the favorite state of a quote and update the state accordingly
    fun toggleFavoriteState(quoteId: Int,oldValue: Boolean){
        viewModelScope.launch {
            val updatedQuotesList = toggleFavoriteStateUseCase(quoteId,oldValue)
            _state = _state.copy(quotes=updatedQuotesList)
        }
    }

}