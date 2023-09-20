package com.newOs.quotivate.quotes.presentation.favoritesList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.newOs.quotivate.R
import com.newOs.quotivate.composables.DefaultIconButton
import com.newOs.quotivate.composables.DefaultText
import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.ui.theme.baby_blue
import com.newOs.quotivate.ui.theme.black
import com.newOs.quotivate.ui.theme.green

@Composable
fun FavoritesScreen() {
    LazyColumn {
        items(getAllFavorites()) {
            FavoriteItem(it)
        }
    }
}

//@Composable
//fun FavoritesScreen() {
//    val vm: QuotesViewModel = viewModel()
//    LazyColumn {
//        items(vm.getAllFavorites()) {
//            FavoriteItem(it)
//        }
//    }
//}

@Composable
fun FavoriteItem(quote: LocalQuote) {
    Card(
        elevation = 6.dp,
        modifier = Modifier.padding(4.dp),
        backgroundColor = baby_blue,
        contentColor = black
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(0.85f)) {
                DefaultText(text = quote.text, fontSize = 20.sp, textColor = black)
                DefaultText(text = quote.author, fontSize = 14.sp, textColor = black)
            }
            DefaultIconButton(
                imageId = R.drawable.delete_icon,
                imageDescription = "Refresh Button",
                borderColor = black,
                iconColor = black,
                backgroundColor = green,
                modifier = Modifier.weight(0.15f),
                onClick = {
                    /* Delete LocalQuote from db */
                })
        }
    }
}


val dummyOne = LocalQuote(id = 1, author = "OsOs",text = "Mohamed Osama Saleh Ahmed Abdallah Nasr Computer & Systems Engineer",isFavorite = false)
val dummyFour = LocalQuote(id = 4,author = "GOAT",text = "Leo",isFavorite = true)

val quoteList = listOf(
    dummyOne,
    dummyFour,
)

fun getAllFavorites() = quoteList.filter { it.isFavorite }