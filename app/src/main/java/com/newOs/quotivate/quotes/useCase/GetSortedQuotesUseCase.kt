package com.newOs.quotivate.quotes.useCase

import com.newOs.quotivate.quotes.repo.QuotesRepository
import com.newOs.quotivate.room.Quote

class GetSortedQuotesUseCase {

    private val quotesRepository = QuotesRepository()

    /* Use to deal with GetSortedQuotesUseCase as function to execute this function directly */
    suspend operator fun invoke(): List<Quote>{
        return quotesRepository.getQuotes().sortedBy { it.text }
    }

}