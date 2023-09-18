package com.newOs.quotivate

data class QQuote(
    val id: Int,
    val author: String,
    val text: String,
    val isFavorite: Boolean=false,
)

val dummyOne = QQuote(1,"OsOs","Mohamed Osama Saleh Ahmed Abdallah Nasr Computer & Systems Engineer",false)
val dummyTwo = QQuote(2,"Nozwa","Nihal")
val dummyThree = QQuote(3,"Weza","Amal",true)
val dummyFour = QQuote(4,"GOAT","Leo",false)
val dummyFive = QQuote(5,"OsOs","Mohamed",false)
val dummySix = QQuote(6, "Nozwa","Nihal",false)
val dummySeven = QQuote(7, "Weza","Amal",true)
val dummyEight = QQuote(8, "GOAT","Leo",false)
val dummyNine = QQuote(9,"OsOs","Mohamed",false)
val dummyTen = QQuote(10,"Nozwa","Nihal",false)
val dummyEleven = QQuote(11,"Weza","Amal",true)
val dummyTwelve = QQuote(12, "GOAT","Leo",false)


val quoteList = listOf(
    dummyOne,
    dummyTwo,
    dummyThree,
    dummyFour,
    dummyFive,
    dummySix,
    dummySeven,
    dummyEight,
    dummyNine,
    dummyTen,
    dummyEleven,
    dummyTwelve
)