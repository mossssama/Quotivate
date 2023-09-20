package com.newOs.quotivate.quotes

import com.newOs.quotivate.QuotesApplication
import com.newOs.quotivate.api.RetrofitClient
import com.newOs.quotivate.helpers.convertToQuoteList
import com.newOs.quotivate.room.FavoriteQuoteState
import com.newOs.quotivate.room.QuoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository {
    private var quotesDao = QuoteDatabase.getInstance(QuotesApplication.getApplicationContext())
    private var apiService = RetrofitClient.api

    suspend fun getQuotesFromDB() = withContext(Dispatchers.IO){
        try {
            updateLocalDatabase()
        }catch (e:Exception){
            if(quotesDao.getAllQuotes().isEmpty()){
                throw Exception("Something went wrong, No data was found, try connecting to the Internet")
            }
        }
        quotesDao.getAllQuotes()
    }

    private suspend fun updateLocalDatabase() {
        val quotes = apiService.getAllQuotes()
        val favoriteQuotesList = quotesDao.getAllFavoriteQuotes()
        quotesDao.addAllQuotes(convertToQuoteList(quotes))
        quotesDao.updateAll(favoriteQuotesList.map { FavoriteQuoteState(id=it.id, isFavorite = true) })
    }

    suspend fun toggleFavoriteQuote(quoteId:Int, newFavoriteState: Boolean) = withContext(Dispatchers.IO){
        quotesDao.updateQuote(FavoriteQuoteState(id = quoteId, isFavorite = newFavoriteState))
        return@withContext quotesDao.getAllQuotes()
    }

}