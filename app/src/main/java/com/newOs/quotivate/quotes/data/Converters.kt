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
    }
}