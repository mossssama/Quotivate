package com.newOs.quotivate.api

import retrofit2.http.*

interface QuotesApiService {
    @GET("randomQuote")
    suspend fun getRandomQuote(): SingleQuote

    @GET("allQuotes")
    suspend fun getAllQuotes(): List<SingleQuote>
}
