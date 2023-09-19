package com.newOs.quotivate.api

import retrofit2.Call
import retrofit2.http.*

interface QuotesApiService {
    @GET("randomQuote")
    fun getRandomQuote(): Call<SingleQuote>

    @GET("allQuotes")
    suspend fun getAllQuotes(): List<SingleQuote>
}
