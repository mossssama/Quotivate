package com.newOs.quotivate.quotes.useCase

import com.newOs.quotivate.quotes.repo.QuotesRepository
import com.newOs.quotivate.room.Quote

class ToggleFavoriteStateUseCase {

    private val quotesRepository= QuotesRepository()
    private val getSortedQuotesUseCase = GetSortedQuotesUseCase()

    /* Use to deal with ToggleFavoriteStateUseCase as function to execute this function directly */
    suspend operator fun invoke(id: Int,oldState: Boolean): List<Quote>{
        val newState = oldState.not()
        quotesRepository.toggleFavoriteQuote(id,newState)
        return getSortedQuotesUseCase()
    }

}