package com.newOs.quotivate.quotes.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.newOs.quotivate.composeUi.composables.DefaultIconButton
import com.newOs.quotivate.quotes.presentation.Screen
import com.newOs.quotivate.composeUi.composables.DefaultTextButton
import com.newOs.quotivate.composeUi.composables.DefaultTextView
import com.newOs.quotivate.composeUi.theme.*

@Composable
fun MainScreen(navController: NavController, state: MainScreenState, onRefresh:()-> Unit, onFavoriteIconClick:(id:Int)-> Unit) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        /* LocalQuote itself */
        DefaultTextView(
            text = state.quote.text,
            fontSize = 80.sp,
            textColor = black,
            backgroundColor = baby_blue,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(4.dp, 4.dp, 4.dp, 1.dp),
            cornersShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        )

        /* LocalQuote author */
        DefaultTextView(
            text = state.quote.author,
            fontSize = 20.sp,
            textColor = black,
            backgroundColor = baby_blue,
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
                .padding(4.dp, 0.dp, 4.dp, 2.dp)
        )

        /* Interaction Buttons */
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp, 2.dp, 3.dp, 2.dp)
        ) {
            DefaultIconButton(
                imageId = com.newOs.quotivate.R.drawable.refresh_icon,
                imageDescription = "Refresh Button",
                borderColor = black,
                iconColor = black,
                backgroundColor = green,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = { onRefresh() }
            )

            DefaultIconButton(
                imageId = com.newOs.quotivate.R.drawable.favorite_icon,
                imageDescription = "Favorite Button",
                borderColor = black,
                iconColor = black,
                backgroundColor = blue,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = { onFavoriteIconClick(state.quote.id)}
            )

            DefaultIconButton(
                imageId = com.newOs.quotivate.R.drawable.share_icon,
                imageDescription = "Share Button",
                borderColor = black,
                iconColor = black,
                backgroundColor = red,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = {}
            )

        }

        DefaultTextButton(
            text = "Favorites",
            modifier = Modifier
                .weight(0.14f)
                .fillMaxWidth()
                .padding(4.dp, 2.dp, 4.dp, 2.dp),
            textColor = black,
            backgroundColor = yellow,
            cornersShape = RoundedCornerShape(topStart = 0.dp),
            onClick = { navController.navigate(Screen.FavoritesScreen.route) }
        )

        DefaultTextButton(
            text = "More",
            modifier = Modifier
                .weight(0.14f)
                .fillMaxWidth()
                .padding(4.dp, 2.dp, 4.dp, 4.dp),
            textColor = black,
            backgroundColor = pink,
            cornersShape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            onClick = { navController.navigate(Screen.QuotesScreen.route) }
        )

    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        { CircularProgressIndicator() }
    }
    state.error?.let { Text(it) }
}