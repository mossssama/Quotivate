package com.newOs.quotivate.helpers

import com.newOs.quotivate.api.SingleQuote
import com.newOs.quotivate.room.Quote

fun convertToQuoteList(singleQuotes: List<SingleQuote>): List<Quote> {
    var idCounter = 1
    return singleQuotes.map { singleQuote ->
        val quote = Quote(id = idCounter, author = singleQuote.author,text = singleQuote.text)
        idCounter++
        quote
    }
}