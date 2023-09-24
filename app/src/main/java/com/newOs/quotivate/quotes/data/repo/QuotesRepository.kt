package com.newOs.quotivate.quotes.data.repo

import com.newOs.quotivate.quotes.data.Converters.Companion.buildLocalQuoteList
import com.newOs.quotivate.quotes.data.Converters.Companion.convertLocalQuoteToQuote
import com.newOs.quotivate.quotes.data.Converters.Companion.convertRemoteQuoteToQuote
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
            if(quotesDao.getQuotes().isEmpty()) throw Exception("Something went wrong, No data was found, try connecting to the Internet")
        }
    }

    suspend fun getRandomQuote(): Quote = withContext(Dispatchers.IO){ convertRemoteQuoteToQuote(apiService.getRandomQuote()) }

    suspend fun getFavorites(): List<Quote> = withContext(Dispatchers.IO){ quotesDao.getFavorites().map { convertLocalQuoteToQuote(it) } }

    suspend fun getQuotes(): List<Quote> = withContext(Dispatchers.IO){ quotesDao.getQuotes().map { convertLocalQuoteToQuote(it) } }

    private suspend fun updateLocalDatabase() {
        val quotes = apiService.getQuotes()
        val favoriteQuotesList = quotesDao.getFavorites()
        quotesDao.addAllQuotes(buildLocalQuoteList(quotes))
        quotesDao.updateAllQuotes(favoriteQuotesList.map { LocalQuoteFavoriteState(id=it.id, isFavorite = true) })
    }

    suspend fun updateQuotes(quoteId:Int, newFavoriteState: Boolean) = withContext(Dispatchers.IO){
        quotesDao.updateQuote(LocalQuoteFavoriteState(id = quoteId, isFavorite = newFavoriteState))
        return@withContext quotesDao.getQuotes()
    }

    suspend fun updateFavorites(quoteId:Int, newFavoriteState: Boolean) = withContext(Dispatchers.IO){
        quotesDao.updateQuote(LocalQuoteFavoriteState(id = quoteId, isFavorite = newFavoriteState))
        return@withContext quotesDao.getFavorites()
    }

    suspend fun addToFavorites(id: Int) {
        quotesDao.updateQuote(LocalQuoteFavoriteState(isFavorite = true,id = id))
    }

}