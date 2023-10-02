package com.newOs.quotivate.quotes.domain.useCases.quotes

import androidx.paging.Pager
import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.quotes.data.repo.QuotesRepository
import javax.inject.Inject

class LoadPagedQuotesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
) {

    operator fun invoke() : Pager<Int, LocalQuote> = quotesRepository.getQuotesPager()
}
