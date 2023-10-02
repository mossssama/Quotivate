package com.newOs.quotivate.quotes.domain.useCases.favorites

import com.newOs.quotivate.quotes.data.repo.QuotesRepository
import com.newOs.quotivate.quotes.domain.entity.Quote
import javax.inject.Inject

class ToggleFavoriteStateUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val getSortedFavoritesUseCase: GetSortedFavoritesUseCase,
){

    suspend operator fun invoke(id: Int,oldState: Boolean): List<Quote>{
        quotesRepository.updateFavorites(id,oldState.not())
        return getSortedFavoritesUseCase()
    }

}