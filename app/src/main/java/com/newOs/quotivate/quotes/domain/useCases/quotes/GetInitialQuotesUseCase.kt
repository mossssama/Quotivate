package com.newOs.quotivate.quotes.domain.useCases.quotes

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import com.newOs.quotivate.quotes.domain.useCases.quotes.GetSortedQuotesUseCase
import javax.inject.Inject

class GetInitialQuotesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getSortedQuotesUseCase: GetSortedQuotesUseCase
) {

    suspend operator fun invoke(): List<Quote>{
        quotesRepository.loadQuotes()
        return getSortedQuotesUseCase()
    }

}