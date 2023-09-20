package com.newOs.quotivate.quotes.presentation.quoteOfDay

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newOs.quotivate.quotes.data.remote.RemoteQuote
import com.newOs.quotivate.quotes.data.remote.RetrofitClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    var state by mutableStateOf(RemoteQuote("",""))

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        /* Execute any code we want incase no internet connection or server is failed*/
    }

    init{ getRandomQuote() }

    internal fun getRandomQuote(){
        viewModelScope.launch(coroutineExceptionHandler) {
            state = getRandomQuoteFromAPI()
        }
    }

    private suspend fun getRandomQuoteFromAPI() = withContext(Dispatchers.IO){ RetrofitClient.api.getRandomQuote() }


}