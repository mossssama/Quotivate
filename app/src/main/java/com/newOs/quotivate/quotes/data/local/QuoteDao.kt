package com.newOs.quotivate.quotes.data.local

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface QuoteDao {
    @Query("SELECT * FROM LocalQuote")
    suspend fun getQuotes(): List<LocalQuote>

    @Query("SELECT * FROM LocalQuote WHERE isFavorite = true")
    suspend fun getFavorites(): List<LocalQuote>

    @Update(entity = LocalQuote::class)
    suspend fun updateQuote(localQuoteFavoriteState: LocalQuoteFavoriteState)

    @Update(entity = LocalQuote::class)
    suspend fun updateAllQuotes(quotesStates: List<LocalQuoteFavoriteState>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quotes: List<LocalQuote>)

    @Query("DELETE FROM LocalQuote WHERE id IN (:idList)")
    suspend fun deletePage(idList: List<Int>)

    @Query("SELECT * FROM LocalQuote ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, LocalQuote>


}
