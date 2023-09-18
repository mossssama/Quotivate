package com.newOs.quotivate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.newOs.quotivate.QuotesViewModel
import com.newOs.quotivate.R
import com.newOs.quotivate.composables.DefaultIconButton
import com.newOs.quotivate.composables.DefaultText
import com.newOs.quotivate.room.Quote
import com.newOs.quotivate.ui.theme.baby_blue
import com.newOs.quotivate.ui.theme.black
import com.newOs.quotivate.ui.theme.green

@Composable
fun QuotesScreen() {
    val vm: QuotesViewModel = viewModel()
    LazyColumn {
        items(vm.getAllQuotes()) { quote ->
            QuoteItem(quote)
        }
    }
}

@Composable
fun QuoteItem(quote: Quote) {
    var isFavorite by remember { mutableStateOf(quote.isFavorite) }
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
                imageId = if (isFavorite) R.drawable.favorite_icon else R.drawable.favorite_hollow_icon,
                imageDescription = "Refresh Button",
                borderColor = black,
                iconColor = black,
                backgroundColor = green,
                modifier = Modifier.weight(0.15f),
                onClick = {
                    isFavorite = !isFavorite
                })

        }
    }
}
