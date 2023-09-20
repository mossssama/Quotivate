package com.newOs.quotivate.quotes.data.remote

import retrofit2.http.*

interface QuotesApiService {
    @GET("randomQuote")
    suspend fun getRandomQuote(): RemoteQuote

    @GET("allQuotes")
    suspend fun getQuotes(): List<RemoteQuote>

    @GET("/paginatedQuotes")
    suspend fun getQuotesPage(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<RemoteQuote>
}
