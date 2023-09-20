package com.newOs.quotivate.api

import com.newOs.quotivate.room.Quote
import retrofit2.http.*

interface QuotesApiService {
    @GET("randomQuote")
    suspend fun getRandomQuote(): SingleQuote

    @GET("allQuotes")
    suspend fun getAllQuotes(): List<SingleQuote>

    @GET("/paginatedQuotes")
    suspend fun getQuotesPage(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Quote>
}
