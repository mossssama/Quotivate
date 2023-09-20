package com.newOs.quotivate.quotes.useCase

import com.newOs.quotivate.quotes.repo.QuotesRepository
import com.newOs.quotivate.room.Quote

class GetInitialQuotesUseCase {

    private val quotesRepository = QuotesRepository()
    private val getSortedQuotesUseCase = GetSortedQuotesUseCase()

    /* Use to deal with GetInitialQuotesUseCase as function to execute this function directly */
    suspend operator fun invoke(): List<Quote>{
        quotesRepository.loadQuotes()
        return getSortedQuotesUseCase()
    }

}