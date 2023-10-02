package com.newOs.quotivate.quotes.presentation.favoritesList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.newOs.quotivate.R
import com.newOs.quotivate.ui.composables.DefaultIconButton
import com.newOs.quotivate.ui.composables.DefaultText
import com.newOs.quotivate.quotes.domain.entity.Quote
import com.newOs.quotivate.ui.theme.baby_blue
import com.newOs.quotivate.ui.theme.black
import com.newOs.quotivate.ui.theme.green

// Composable to display the list of favorite quotes
@Composable
fun FavoritesScreen(state: FavoritesScreenState, onFavoriteIconClick:(id:Int, oldValue:Boolean)-> Unit) {
    LazyColumn {
        items(state.quotes) { quote ->
            FavoriteItem(
                quote = quote,
                onFavoriteIconClick = { id,oldValue ->
                    onFavoriteIconClick(id,oldValue)
                }
            )
        }
    }

    // If the screen is in a loading state, show a loading indicator
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
    }

    // If there is an error in the state, display an error message
    state.error?.let { Text(it) }
}

// Composable to display a single favorite quote item
@Composable
fun FavoriteItem(quote: Quote, onFavoriteIconClick: (Int, Boolean)->Unit) {
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
                onClick = { onFavoriteIconClick(quote.id,quote.isFavorite) }
            )
        }
    }
}
