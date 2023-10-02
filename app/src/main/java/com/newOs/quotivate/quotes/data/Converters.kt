package com.newOs.quotivate.quotes.data

import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.quotes.data.remote.RemoteQuote
import com.newOs.quotivate.quotes.domain.entity.Quote

class Converters {
    companion object {
        fun buildLocalQuoteList(singleQuotes: List<RemoteQuote>): List<LocalQuote> = singleQuotes.map { convertRemoteQuoteToLocalQuote(it) }

        fun convertRemoteQuoteToQuote(remoteQuote: RemoteQuote): Quote = Quote(author = remoteQuote.author, text = remoteQuote.text, id = remoteQuote.id)

        fun convertLocalQuoteToQuote(localQuote: LocalQuote): Quote = Quote(localQuote.author,localQuote.text,localQuote.isFavorite,localQuote.id)

        private fun convertRemoteQuoteToLocalQuote(remoteQuote: RemoteQuote): LocalQuote = LocalQuote(author = remoteQuote.author, text = remoteQuote.text, id = remoteQuote.id)

        fun convertRemoteQuotesToLocalQuotes(remoteQuotes: List<RemoteQuote>): List<LocalQuote> {
            return remoteQuotes.map { remoteQuote ->
                LocalQuote(
                    author = remoteQuote.author,
                    text = remoteQuote.text,
                    id = remoteQuote.id
                )
            }
        }

        fun getPageNumber(quoteId: Int,pageSize: Int): Int = (quoteId - 1) / pageSize + 1

        fun getQuoteIDsForPage(pageNumber: Int, pageSize: Int): List<Int> {
            val startId = (pageNumber - 1) * pageSize + 1
            val endId = startId + pageSize - 1

            return (startId..endId).toList()
        }

    }
}