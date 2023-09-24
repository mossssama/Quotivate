package com.newOs.quotivate.quotes.domain.useCases

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import com.newOs.quotivate.quotes.domain.useCases.quotes.GetSortedQuotesUseCase
import javax.inject.Inject

class ToggleRandomQuoteStateUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
){

    suspend operator fun invoke(id: Int){
        quotesRepository.updateQuoteState(id)
    }

}