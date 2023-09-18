package com.newOs.quotivate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.newOs.quotivate.QuotesViewModel
import com.newOs.quotivate.R
import com.newOs.quotivate.composables.IconBtn
import com.newOs.quotivate.composables.Txt
import com.newOs.quotivate.room.Quote
import com.newOs.quotivate.ui.theme.baby_blue
import com.newOs.quotivate.ui.theme.black
import com.newOs.quotivate.ui.theme.green

@Composable
fun QuotesScreen() {
    val vm: QuotesViewModel = viewModel()
    LazyColumn{
        items(vm.getAllQuotes()) { quote ->
            QuoteItem(quote)
        }
    }
}

@Composable
fun QuoteItem(quote: Quote){
    var currentState by remember { mutableStateOf(quote.isFavorite) }
    Card(elevation = 6.dp, modifier = Modifier.padding(4.dp), backgroundColor = baby_blue,contentColor = black) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(0.85f)) {
                QText(quoteText = quote.text,textColor = black)
                QAuthor(quoteAuthor = quote.author,textColor = black)
            }
            AddToFavorite(modifier = Modifier.weight(0.15f), imageId = if(currentState) R.drawable.favorite_icon else R.drawable.favorite_hollow_icon, onClick = {
                currentState=!currentState
            })
        }
    }
}

@Composable
fun QText(quoteText:String,textColor: Color) {
    Txt(text = quoteText,fontSize = 20.sp,textColor=textColor)
}

@Composable
fun QAuthor(quoteAuthor:String,textColor: Color) {
    Txt(text = quoteAuthor,fontSize = 14.sp,textColor=textColor)
}

@Composable
fun AddToFavorite(modifier: Modifier,imageId:Int,onClick: ()->Unit) {
    IconBtn(imageId = imageId, imageDescription = "Refresh Button", borderColor = black, iconColor = black,backgroundColor = green,modifier = modifier, onClick = onClick)
}
