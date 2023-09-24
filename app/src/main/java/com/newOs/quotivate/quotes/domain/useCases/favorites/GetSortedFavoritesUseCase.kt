package com.newOs.quotivate.quotes.domain.useCases.favorites

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class GetSortedFavoritesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
){

    suspend operator fun invoke(): List<Quote>{
        return quotesRepository.getFavorites().sortedBy { it.text }
    }

}