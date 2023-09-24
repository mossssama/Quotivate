package com.newOs.quotivate.quotes.domain.useCases;

import com.newOs.quotivate.quotes.data.QuotesRepository;
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quotesRepository:QuotesRepository
){

    suspend operator fun invoke():Quote = quotesRepository.getRandomQuote()

}