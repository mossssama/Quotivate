package com.newOs.quotivate.quotes.data.repo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.newOs.quotivate.quotes.data.remote.QuotesApiService
import com.newOs.quotivate.quotes.data.remote.RemoteQuote

class QuotesPagingSource(
    private val apiService: QuotesApiService
) : PagingSource<Int, RemoteQuote>() {

    override fun getRefreshKey(state: PagingState<Int, RemoteQuote>): Int? {
        val prevKey = state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
        return prevKey
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteQuote> {
        return try {
            val pageNo = params.key ?: 1
            val quotes = apiService.getQuotesPage(pageNo, 11)
            val prevKey = if (pageNo > 1) pageNo - 1 else null

            Log.i("OsOs","$prevKey")

            LoadResult.Page(
                data = quotes,
                prevKey = prevKey,
                nextKey = if (quotes.isEmpty()) null else pageNo + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}
