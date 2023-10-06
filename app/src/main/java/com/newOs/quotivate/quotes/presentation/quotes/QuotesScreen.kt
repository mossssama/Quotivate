package com.newOs.quotivate.quotes.presentation.quotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.newOs.quotivate.R
import com.newOs.quotivate.quotes.data.Converters.Companion.convertLocalQuoteToQuote
import com.newOs.quotivate.quotes.data.local.LocalQuote
import com.newOs.quotivate.composeUi.composables.DefaultIconButton
import com.newOs.quotivate.composeUi.composables.DefaultText
import com.newOs.quotivate.composeUi.theme.*
import com.newOs.quotivate.quotes.domain.entity.Quote
import kotlinx.coroutines.flow.Flow

// Displays a list of quotes using LazyColumn and QuoteItem components.
@Composable
fun QuotesScreen(quotes: Flow<PagingData<LocalQuote>>, onFavoriteIconClick:(id:Int, oldValue:Boolean)-> Unit) {

    val lazyPagingItems = quotes.collectAsLazyPagingItems()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBackground)
        ) {
            items(lazyPagingItems.itemCount) { index ->
                QuoteItem(
                    quote = convertLocalQuoteToQuote(lazyPagingItems[index]!!),
                    onFavoriteIconClick = { id,oldValue -> onFavoriteIconClick(id,oldValue) }
                )
            }
        }
    }

}

// Displays a single quote inside a Card
@Composable
fun QuoteItem(quote: Quote, onFavoriteIconClick: (Int, Boolean)->Unit) {
    Card(
        elevation = 6.dp,
        modifier = Modifier.padding(4.dp),
        backgroundColor = white,
        contentColor = marron
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(0.85f)) {
                DefaultText(text = quote.text, fontSize = 20.sp, textColor = black)
                DefaultText(text = quote.author, fontSize = 14.sp, textColor = black)
            }
            DefaultIconButton(
                imageId = if (quote.isFavorite) R.drawable.favorite_icon else R.drawable.favorite_hollow_icon,
                imageDescription = "Refresh Button",
                borderColor = dark_marron,
                iconColor = marron,
                backgroundColor = white,
                modifier = Modifier.weight(0.15f),
                onClick = { onFavoriteIconClick(quote.id,quote.isFavorite) }
            )

        }
    }
}
