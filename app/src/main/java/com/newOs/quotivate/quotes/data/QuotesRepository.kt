package com.newOs.quotivate.quotes.data

import com.newOs.quotivate.QuotesApplication
import com.newOs.quotivate.quotes.data.remote.RetrofitClient
import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.quotes.data.local.LocalQuoteFavoriteState
import com.newOs.quotivate.quotes.domain.Quote
import com.newOs.quotivate.quotes.data.local.QuoteDatabase
import com.newOs.quotivate.quotes.data.remote.RemoteQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository {
    private var quotesDao = QuoteDatabase.getInstance(QuotesApplication.getApplicationContext())
    private var apiService = RetrofitClient.api

    suspend fun loadQuotes() = withContext(Dispatchers.IO){
        try {
            updateLocalDatabase()
        }catch (e:Exception){
            if(quotesDao.getAll().isEmpty()){
                throw Exception("Something went wrong, No data was found, try connecting to the Internet")
            }
        }
    }

    suspend fun getQuotes(): List<Quote> = withContext(Dispatchers.IO){
        return@withContext quotesDao.getAll().map {
            Quote(author = it.author,text = it.text, isFavorite = it.isFavorite, id = it.id)
        }
    }


    private suspend fun updateLocalDatabase() {
        val quotes = apiService.getQuotes()
        val favoriteQuotesList = quotesDao.getFavoriteQuotes()
//        quotesDao.addAll(quotes.map {
//            LocalQuote(author = it.author,text = it.text)
//        })
        quotesDao.addAll(convertToLocalQuoteList(quotes))
        quotesDao.updateAll(favoriteQuotesList.map { LocalQuoteFavoriteState(id=it.id, isFavorite = true) })
    }

    suspend fun toggleFavoriteQuote(quoteId:Int, newFavoriteState: Boolean) = withContext(Dispatchers.IO){
        quotesDao.updateQuote(LocalQuoteFavoriteState(id = quoteId, isFavorite = newFavoriteState))
        return@withContext quotesDao.getAll()
    }

    fun convertToLocalQuoteList(singleQuotes: List<RemoteQuote>): List<LocalQuote> {
        var idCounter = 1
        return singleQuotes.map { singleQuote ->
            val quote = LocalQuote(id = idCounter, author = singleQuote.author, text = singleQuote.text)
            idCounter++
            quote
        }
    }
}