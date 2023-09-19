package com.newOs.quotivate

import com.newOs.quotivate.api.SingleQuote
import com.newOs.quotivate.room.Quote

val dummyOne = Quote(id = 1, author = "OsOs",text = "Mohamed Osama Saleh Ahmed Abdallah Nasr Computer & Systems Engineer",isFavorite = false)
val dummyFour = Quote(id = 4,author = "GOAT",text = "Leo",isFavorite = true)

val quoteList = listOf(
    dummyOne,
    dummyFour,
)

fun convertToQuoteList(singleQuotes: List<SingleQuote>): List<Quote> {
    var idCounter = 1
    return singleQuotes.map { singleQuote ->
        val quote = Quote(id = idCounter, author = singleQuote.author,text = singleQuote.text,isFavorite = false)
        idCounter++
        quote
    }
}