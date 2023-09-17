package com.newOs.quotivate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.newOs.quotivate.composables.IconBtn
import com.newOs.quotivate.composables.TextBtn
import com.newOs.quotivate.composables.TxtView
import com.newOs.quotivate.navigation.Screen
import com.newOs.quotivate.ui.theme.*

@Composable
fun MainScreen(navController: NavController) {
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
                .padding(4.dp, 4.dp, 4.dp, 1.dp)
        )
        QuoteAuthor(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
                .padding(4.dp, 0.dp, 4.dp, 2.dp)
        )

        InteractionRow(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp, 2.dp, 3.dp, 2.dp)
        )

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
fun InteractionRow(modifier: Modifier){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        RefreshQuote(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
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
}



@Composable
fun QuoteText(modifier: Modifier) {
    TxtView(text = "Quote",fontSize = 80.sp,textColor = black, backgroundColor = baby_blue,modifier = modifier, cornersShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
}

@Composable
fun QuoteAuthor(modifier: Modifier) {
    TxtView(text = "Author",fontSize = 16.sp,textColor = black, backgroundColor = baby_blue,modifier = modifier, cornersShape = RoundedCornerShape(topStart=0.dp))
}

@Composable
fun RefreshQuote(modifier: Modifier) {
    IconBtn(imageId = com.newOs.quotivate.R.drawable.refresh_icon, imageDescription = "Refresh Button", borderColor = black, iconColor = black,backgroundColor = green,modifier = modifier)
}

@Composable
fun FavQuote(modifier: Modifier) {
    IconBtn(imageId = com.newOs.quotivate.R.drawable.favorite_icon, imageDescription = "Favorite Button", borderColor = black, iconColor = black,backgroundColor = blue,modifier = modifier)
}

@Composable
fun ShareQuote(modifier: Modifier) {
    IconBtn(imageId = com.newOs.quotivate.R.drawable.share_icon, imageDescription = "Share Button", borderColor = black, iconColor = black, backgroundColor = red,modifier = modifier)
}

@Composable
fun ShowFavoriteQuotes(modifier: Modifier, navController: NavController) {
    TextBtn("Favorites",modifier, textColor = black, backgroundColor = yellow, cornersShape = RoundedCornerShape(topStart=0.dp), onClick = { navController.navigate(
        Screen.FavoritesScreen.route) })
}

@Composable
fun ShowMoreQuotes(modifier: Modifier, navController: NavController) {
    TextBtn("More",modifier, textColor = black, backgroundColor = pink, cornersShape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp), onClick = { navController.navigate(
        Screen.QuotesScreen.route) })
}




