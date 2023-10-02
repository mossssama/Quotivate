package com.newOs.quotivate.quotes.domain.useCases.quotes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.newOs.quotivate.quotes.data.remote.RemoteQuote
import com.newOs.quotivate.quotes.data.repo.QuotesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadPagedQuotesUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
) {

    operator fun invoke(): Flow<PagingData<RemoteQuote>> = Pager(
        config = PagingConfig(11),
        pagingSourceFactory = { quotesRepository.getQuotesPagingSource() }
    ).flow.cachedIn(GlobalScope)

}
