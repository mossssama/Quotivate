package com.newOs.quotivate.quotes.domain.useCases

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class ToggleFavoriteStateUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getSortedQuotesUseCase: GetSortedQuotesUseCase,
){

//    private val quotesRepository= QuotesRepository()
//    private val getSortedQuotesUseCase = GetSortedQuotesUseCase()

    /* Use to deal with ToggleFavoriteStateUseCase as function to execute this function directly */
    suspend operator fun invoke(id: Int,oldState: Boolean): List<Quote>{
        val newState = oldState.not()
        quotesRepository.toggleFavoriteQuote(id,newState)
        return getSortedQuotesUseCase()
    }

}