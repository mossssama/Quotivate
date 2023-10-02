package com.newOs.quotivate.quotes.data.repo

import android.net.http.HttpException
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.*
import com.newOs.quotivate.quotes.data.Constants.Companion.PAGE_SIZE
import com.newOs.quotivate.quotes.data.Converters.Companion.convertRemoteQuotesToLocalQuotes
import com.newOs.quotivate.quotes.data.Converters.Companion.getPageNumber
import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.quotes.data.local.QuoteDao
import com.newOs.quotivate.quotes.data.remote.QuotesApiService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class QuotesRemoteMediator(
    private val dao: QuoteDao,
    private val networkService: QuotesApiService
) : RemoteMediator<Int, LocalQuote>() {

    @RequiresApi(34)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalQuote>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1 // For refresh, start from the first page
                LoadType.PREPEND -> { return MediatorResult.Success(endOfPaginationReached = true) }
                LoadType.APPEND -> { getPageNumber(quoteId = state.lastItemOrNull()?.id?:1, pageSize = PAGE_SIZE) + 1 }
            }

            Log.i("OsOs","PageNumber is: $page")

            val response = networkService.getQuotesPage(
                page = page,
                pageSize = PAGE_SIZE,
            )

            Log.i("OsOs", "Response size: ${response.size}")
            Log.i("OsOs", "Response contents: $response")

            coroutineScope {
                launch {
                    val insertedIds = dao.insertAll(convertRemoteQuotesToLocalQuotes(response))
                    Log.i("OsOs", "Inserted IDs: $insertedIds")
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
