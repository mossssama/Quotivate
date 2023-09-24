package com.newOs.quotivate.quotes.domain.useCases.quotes

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class GetSortedQuotesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
){

    suspend operator fun invoke(): List<Quote>{
        return quotesRepository.getQuotes().sortedBy { it.text }
    }

}