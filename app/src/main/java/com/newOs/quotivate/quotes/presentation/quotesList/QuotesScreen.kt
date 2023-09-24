package com.newOs.quotivate.quotes.presentation.quotesList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.newOs.quotivate.R
import com.newOs.quotivate.quotes.data.Converters.Companion.convertRemoteQuoteToQuote
import com.newOs.quotivate.quotes.data.remote.RemoteQuote
import com.newOs.quotivate.ui.composables.DefaultIconButton
import com.newOs.quotivate.ui.composables.DefaultText
import com.newOs.quotivate.quotes.domain.Quote
import com.newOs.quotivate.ui.theme.baby_blue
import com.newOs.quotivate.ui.theme.black
import com.newOs.quotivate.ui.theme.green

@Composable
fun QuotesScreen(quotes: LazyPagingItems<RemoteQuote>, onFavoriteIconClick:(id:Int, oldValue:Boolean)-> Unit) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn {
            items(quotes.itemCount) { index ->
                QuoteItem(
                    quote = convertRemoteQuoteToQuote(quotes[index]!!),
                    onFavoriteIconClick = { id,oldValue ->
                        onFavoriteIconClick(id,oldValue)
                    }
                )
            }
        }
    }

}

@Composable
fun QuoteItem(quote: Quote, onFavoriteIconClick: (Int,Boolean)->Unit) {
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
                imageId = if (quote.isFavorite) R.drawable.favorite_icon else R.drawable.favorite_hollow_icon,
                imageDescription = "Refresh Button",
                borderColor = black,
                iconColor = black,
                backgroundColor = green,
                modifier = Modifier.weight(0.15f),
                onClick = { onFavoriteIconClick(quote.id,quote.isFavorite) }
            )

        }
    }
}
