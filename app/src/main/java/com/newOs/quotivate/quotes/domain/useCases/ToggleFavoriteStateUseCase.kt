package com.newOs.quotivate.quotes.domain.useCases

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class ToggleFavoriteStateUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getSortedQuotesUseCase: GetSortedQuotesUseCase,
){

    suspend operator fun invoke(id: Int,oldState: Boolean): List<Quote>{
        val newState = oldState.not()
        quotesRepository.toggleFavoriteQuote(id,newState)
        return getSortedQuotesUseCase()
    }

}