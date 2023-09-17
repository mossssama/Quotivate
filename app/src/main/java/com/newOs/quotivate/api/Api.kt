package com.newOs.quotivate.api

import retrofit2.http.*

interface Api {
    @GET("randomQuote")
    suspend fun getRandomQuote(): SingleQuote

    @GET("allQuotes")
    suspend fun getAllQuotes(): List<SingleQuote>
}
