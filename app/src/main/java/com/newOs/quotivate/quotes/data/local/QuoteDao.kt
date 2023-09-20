package com.newOs.quotivate.quotes.data.local

import androidx.room.*

@Dao
interface QuoteDao {
    @Query("SELECT * FROM LocalQuote ORDER BY author ASC")
    suspend fun getAll(): List<LocalQuote>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addAll(quotes: List<LocalQuote>)

    @Update(entity = LocalQuote::class)
    suspend fun updateQuote(localQuoteFavoriteState: LocalQuoteFavoriteState)

    @Query("SELECT * FROM LocalQuote WHERE isFavorite = true")
    suspend fun getFavoriteQuotes(): List<LocalQuote>

    @Update(entity = LocalQuote::class)
    suspend fun updateAll(quotesStates: List<LocalQuoteFavoriteState>)

//    @Insert
//    suspend fun insertQuote(quote: LocalQuote)
//
//    @Query("DELETE FROM LocalQuote WHERE id = :quoteId")
//    suspend fun deleteQuote(quoteId:Int)
//
//    @Query("DELETE FROM LocalQuote")
//    suspend fun clearQuotes()

}
