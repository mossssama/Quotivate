package com.newOs.quotivate.quotes.domain.useCases.main

import com.newOs.quotivate.quotes.data.repo.QuotesRepository
import javax.inject.Inject

class ToggleRandomQuoteStateUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
){

    suspend operator fun invoke(id: Int){
        quotesRepository.addToFavorites(id)
    }

}