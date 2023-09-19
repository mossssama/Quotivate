package com.newOs.quotivate.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuoteDao {
    @Insert
    suspend fun insertQuote(quote: Quote)

    @Query("DELETE FROM Quote WHERE id = :quoteId")
    suspend fun deleteQuote(quoteId:Int)

    @Query("SELECT * FROM Quote ORDER BY author ASC")
    suspend fun getAllQuotes(): List<Quote>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addAllQuotes(quotes: List<Quote>)

    @Query("SELECT * FROM Quote WHERE isFavorite = true ORDER BY author ASC")
    fun getAllFavoriteQuotes(): LiveData<List<Quote>>

    @Query("DELETE FROM Quote")
    suspend fun clearQuotes()
}
