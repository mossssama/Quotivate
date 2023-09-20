package com.newOs.quotivate.quotes.domain.useCases

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class GetSortedQuotesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
){

//    private val quotesRepository = QuotesRepository()

    /* Use to deal with GetSortedQuotesUseCase as function to execute this function directly */
    suspend operator fun invoke(): List<Quote>{
        return quotesRepository.getQuotes().sortedBy { it.text }
    }

}