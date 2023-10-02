package com.newOs.quotivate.quotes.domain.useCases.quotes

import com.newOs.quotivate.quotes.data.repo.QuotesRepository
import com.newOs.quotivate.quotes.domain.entity.Quote
import javax.inject.Inject

class ToggleQuoteStateUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getSortedQuotesUseCase: GetSortedQuotesUseCase,
){

    suspend operator fun invoke(id: Int,oldState: Boolean): List<Quote>{
        quotesRepository.updateQuotes(id,oldState.not())
        return getSortedQuotesUseCase()
    }

}