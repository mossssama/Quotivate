package com.newOs.quotivate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.newOs.quotivate.QuoteViewModel
import com.newOs.quotivate.composables.IconBtn
import com.newOs.quotivate.composables.TextBtn
import com.newOs.quotivate.composables.TxtView
import com.newOs.quotivate.navigation.Screen
import com.newOs.quotivate.ui.theme.*

@Composable
fun MainScreen(navController: NavController,viewModel: QuoteViewModel) {

    var currentQuote by remember { mutableStateOf(viewModel.getRandomQuote())}

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        QuoteText(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(4.dp, 4.dp, 4.dp, 1.dp),
            quoteText = currentQuote.text
        )
        QuoteAuthor(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
                .padding(4.dp, 0.dp, 4.dp, 2.dp),
            quoteAuthor = currentQuote.author
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp, 2.dp, 3.dp, 2.dp)
        ) {
            RefreshQuote(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = { currentQuote = viewModel.getRandomQuote() }
            )
            FavQuote(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            ShareQuote(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }

        ShowFavoriteQuotes(
            modifier = Modifier
                .weight(0.14f)
                .fillMaxWidth()
                .padding(4.dp, 2.dp, 4.dp, 2.dp),
            navController = navController
        )

        ShowMoreQuotes(
            modifier = Modifier
                .weight(0.14f)
                .fillMaxWidth()
                .padding(4.dp, 2.dp, 4.dp, 4.dp),
            navController = navController
        )
    }
}

@Composable
fun QuoteText(modifier: Modifier,quoteText: String) {
    TxtView(text = quoteText,fontSize = 80.sp,textColor = black, backgroundColor = baby_blue,modifier = modifier, cornersShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
}

@Composable
fun QuoteAuthor(modifier: Modifier,quoteAuthor: String) {
    TxtView(text = quoteAuthor,fontSize = 16.sp,textColor = black, backgroundColor = baby_blue,modifier = modifier, cornersShape = RoundedCornerShape(topStart=0.dp))
}

@Composable
fun RefreshQuote(modifier: Modifier,onClick: ()->Unit) {
    IconBtn(imageId = com.newOs.quotivate.R.drawable.refresh_icon, imageDescription = "Refresh Button", borderColor = black, iconColor = black,backgroundColor = green,modifier = modifier, onClick = onClick)
}

@Composable
fun FavQuote(modifier: Modifier) {
    IconBtn(imageId = com.newOs.quotivate.R.drawable.favorite_icon, imageDescription = "Favorite Button", borderColor = black, iconColor = black,backgroundColor = blue,modifier = modifier, onClick = {})
}

@Composable
fun ShareQuote(modifier: Modifier) {
    IconBtn(imageId = com.newOs.quotivate.R.drawable.share_icon, imageDescription = "Share Button", borderColor = black, iconColor = black, backgroundColor = red,modifier = modifier, onClick = {})
}

@Composable
fun ShowFavoriteQuotes(modifier: Modifier, navController: NavController) {
    TextBtn("Favorites",modifier, textColor = black, backgroundColor = yellow, cornersShape = RoundedCornerShape(topStart=0.dp), onClick = { navigateToFavoritesScreen(navController) })
}

@Composable
fun ShowMoreQuotes(modifier: Modifier, navController: NavController) {
    TextBtn("More",modifier, textColor = black, backgroundColor = pink, cornersShape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp), onClick = { navigateToQuotesScreen(navController) })
}

fun navigateToFavoritesScreen(navController: NavController){
    navController.navigate(Screen.FavoritesScreen.route)
}

fun navigateToQuotesScreen(navController: NavController){
    navController.navigate(Screen.QuotesScreen.route)
}


