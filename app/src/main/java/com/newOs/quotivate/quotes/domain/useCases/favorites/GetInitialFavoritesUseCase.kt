package com.newOs.quotivate.quotes.domain.useCases.favorites

import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class GetInitialFavoritesUseCase @Inject constructor(
    private val getSortedFavoritesUseCase: GetSortedFavoritesUseCase
) {

    suspend operator fun invoke(): List<Quote> = getSortedFavoritesUseCase()


}