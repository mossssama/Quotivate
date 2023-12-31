package com.newOs.quotivate.quotes.domain.useCases.quotes

import com.newOs.quotivate.quotes.data.repo.QuotesRepository
import com.newOs.quotivate.quotes.domain.entity.Quote
import javax.inject.Inject

class GetInitialQuotesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
){

    suspend operator fun invoke(): List<Quote> = quotesRepository.getQuotes()

}