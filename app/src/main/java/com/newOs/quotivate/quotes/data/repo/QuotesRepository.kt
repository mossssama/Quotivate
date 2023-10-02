package com.newOs.quotivate.quotes.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.newOs.quotivate.quotes.data.repo.Constants.Companion.PAGE_SIZE
import com.newOs.quotivate.quotes.data.Converters.Companion.convertLocalQuoteToQuote
import com.newOs.quotivate.quotes.data.Converters.Companion.convertRemoteQuoteToQuote
import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.quotes.data.local.LocalQuoteFavoriteState
import com.newOs.quotivate.quotes.data.local.QuoteDao
import com.newOs.quotivate.quotes.data.remote.QuotesApiService
import com.newOs.quotivate.quotes.domain.entity.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesRepository @Inject constructor(
    private val quotesDao: QuoteDao,
    private val apiService: QuotesApiService
) {

    suspend fun getRandomQuote(): Quote = withContext(Dispatchers.IO){ convertRemoteQuoteToQuote(apiService.getRandomQuote()) }

    suspend fun getFavorites(): List<Quote> = withContext(Dispatchers.IO){ quotesDao.getFavorites().map { convertLocalQuoteToQuote(it) } }

    suspend fun getQuotes(): List<Quote> = withContext(Dispatchers.IO){ quotesDao.getQuotes().map { convertLocalQuoteToQuote(it) } }


    @OptIn(ExperimentalPagingApi::class)
    fun getQuotesPager(): Pager<Int, LocalQuote> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = QuotesRemoteMediator(
                quoteDao = quotesDao,
                quotesApiService = apiService
            )
        ) {
            quotesDao.pagingSource()
        }
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