package com.newOs.quotivate.quotes.data.local

import androidx.room.*

@Dao
interface QuoteDao {
    @Query("SELECT * FROM LocalQuote ORDER BY author ASC")
    suspend fun getQuotes(): List<LocalQuote>

    @Query("SELECT * FROM LocalQuote WHERE isFavorite = true")
    suspend fun getFavorites(): List<LocalQuote>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addAllQuotes(quotes: List<LocalQuote>)

    @Update(entity = LocalQuote::class)
    suspend fun updateQuote(localQuoteFavoriteState: LocalQuoteFavoriteState)

    @Update(entity = LocalQuote::class)
    suspend fun updateAllQuotes(quotesStates: List<LocalQuoteFavoriteState>)
}
