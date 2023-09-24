package com.newOs.quotivate.quotes.data

import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.quotes.data.local.LocalQuoteFavoriteState
import com.newOs.quotivate.quotes.data.local.QuoteDao
import com.newOs.quotivate.quotes.data.remote.QuotesApiService
import com.newOs.quotivate.quotes.domain.Quote
import com.newOs.quotivate.quotes.data.remote.RemoteQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesRepository @Inject constructor(
    private val quotesDao: QuoteDao,
    private val apiService: QuotesApiService
) {

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

    suspend fun getFavorites(): List<Quote> = withContext(Dispatchers.IO){
        return@withContext quotesDao.getFavoriteQuotes().map {
            Quote(author = it.author,text = it.text, isFavorite = it.isFavorite, id = it.id)
        }
    }

    private suspend fun updateLocalDatabase() {
        val quotes = apiService.getQuotes()
        val favoriteQuotesList = quotesDao.getFavoriteQuotes()
        quotesDao.addAll(convertToLocalQuoteList(quotes))
        quotesDao.updateAll(favoriteQuotesList.map { LocalQuoteFavoriteState(id=it.id, isFavorite = true) })
    }

    suspend fun toggleQuote(quoteId:Int, newFavoriteState: Boolean) = withContext(Dispatchers.IO){
        quotesDao.updateQuote(LocalQuoteFavoriteState(id = quoteId, isFavorite = newFavoriteState))
        return@withContext quotesDao.getAll()
    }

    suspend fun toggleFavorite(quoteId:Int, newFavoriteState: Boolean) = withContext(Dispatchers.IO){
        quotesDao.updateQuote(LocalQuoteFavoriteState(id = quoteId, isFavorite = newFavoriteState))
        return@withContext quotesDao.getFavoriteQuotes()
    }

    private fun convertToLocalQuoteList(singleQuotes: List<RemoteQuote>): List<LocalQuote> {
        var idCounter = 1
        return singleQuotes.map { singleQuote ->
            val quote = LocalQuote(id = idCounter, author = singleQuote.author, text = singleQuote.text)
            idCounter++
            quote
        }
    }
}