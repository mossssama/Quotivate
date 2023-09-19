package com.newOs.quotivate.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuoteDao {
    @Query("SELECT * FROM Quote ORDER BY author ASC")
    suspend fun getAllQuotes(): List<Quote>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addAllQuotes(quotes: List<Quote>)

    @Update(entity = Quote::class)
    suspend fun updateQuote(favoriteQuoteState: FavoriteQuoteState)

    @Query("SELECT * FROM Quote WHERE isFavorite = true")
    suspend fun getAllFavoriteQuotes(): List<Quote>

    @Update(entity = Quote::class)
    suspend fun updateAll(quotesStates: List<FavoriteQuoteState>)









    @Insert
    suspend fun insertQuote(quote: Quote)

    @Query("DELETE FROM Quote WHERE id = :quoteId")
    suspend fun deleteQuote(quoteId:Int)

    @Query("DELETE FROM Quote")
    suspend fun clearQuotes()
}
