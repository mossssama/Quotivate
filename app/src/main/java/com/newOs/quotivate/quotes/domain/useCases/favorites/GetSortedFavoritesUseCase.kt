package com.newOs.quotivate.quotes.domain.useCases.favorites

import com.newOs.quotivate.quotes.data.repo.QuotesRepository
import com.newOs.quotivate.quotes.domain.entity.Quote
import javax.inject.Inject

class GetSortedFavoritesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
){

    suspend operator fun invoke(): List<Quote> = quotesRepository.getFavorites().sortedBy { it.text }

}