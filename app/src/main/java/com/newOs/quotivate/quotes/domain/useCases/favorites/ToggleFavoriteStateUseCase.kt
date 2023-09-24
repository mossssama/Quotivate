package com.newOs.quotivate.quotes.domain.useCases.favorites;

import com.newOs.quotivate.quotes.data.QuotesRepository
import com.newOs.quotivate.quotes.domain.Quote
import javax.inject.Inject

class ToggleFavoriteStateUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getSortedFavoritesUseCase: GetSortedFavoritesUseCase,
        ){

    suspend operator fun invoke(id: Int,oldState: Boolean): List<Quote>{
        val newState = oldState.not()
        quotesRepository.toggleFavorite(id,newState)
        return getSortedFavoritesUseCase()
    }

}