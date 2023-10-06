package com.newOs.quotivate.quotes.presentation.main

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.quotes.domain.entity.Quote
import com.newOs.quotivate.quotes.domain.useCases.main.GetRandomQuoteUseCase
import com.newOs.quotivate.quotes.domain.useCases.main.ToggleRandomQuoteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val toggleRandomQuoteStateUseCase: ToggleRandomQuoteStateUseCase
): ViewModel() {
    // Mutable State to hold the current state of the Main Screen
    private var _state by mutableStateOf(
        MainScreenState(
            quote = Quote("","",false),
            isLoading = true
        )
    )
    // Expose an Immutable state to prevent direct modification from the UI
    val state: State<MainScreenState>
        get() = derivedStateOf { _state }

    // Coroutine handler to handle errors during data loading
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state = _state.copy(
            isLoading = false,
            error = if(throwable.message=="timeout") "Bad Internet connection; try Again" else "De7k"
        )
    }

    // Initialize the state by fetching random Quote
    init{ getRandomQuote() }

    fun getRandomQuote(){
        loadingForRandomQuote()
        viewModelScope.launch(coroutineExceptionHandler) {
            val receivedQuote = getRandomQuoteUseCase()
            _state = _state.copy(
                quote = receivedQuote,
                isLoading = false,
                error = ""
            )
        }
    }

    // Fetch initial random quote from a data source
    private fun loadingForRandomQuote(){
        _state = _state.copy(
            quote = Quote("",""),
            isLoading = true,
            error = ""
        )
    }

    // Toggle the favorite state of a quote and update the state accordingly
    fun toggleFavoriteState(id: Int) {
        viewModelScope.launch {
            toggleRandomQuoteStateUseCase(id)
        }
    }

    fun shareQuote(quote: Quote,context: Context) {
        val shareText = "${quote.text}\n{${quote.author}}"
        val url = "https://quotes-dmug.onrender.com/stringQuote/${quote.id}"

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "$shareText\n$url")

        context.startActivity(Intent.createChooser(intent, "Share Quote via"))
    }

}