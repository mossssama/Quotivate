package com.newOs.quotivate.quotes.domain.useCases

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class GetInitialQuotesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getSortedQuotesUseCase: GetSortedQuotesUseCase
) {

//    private val quotesRepository = QuotesRepository()
//    private val getSortedQuotesUseCase = GetSortedQuotesUseCase()

    /* Use to deal with GetInitialQuotesUseCase as function to execute this function directly */
    suspend operator fun invoke(): List<Quote>{
        quotesRepository.loadQuotes()
        return getSortedQuotesUseCase()
    }

}