package com.newOs.quotivate.quotes.presentation.main

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.newOs.quotivate.composeUi.composables.DefaultIconButton
import com.newOs.quotivate.quotes.presentation.Screen
import com.newOs.quotivate.composeUi.composables.DefaultTextButton
import com.newOs.quotivate.composeUi.composables.DefaultTextView
import com.newOs.quotivate.composeUi.theme.*
import com.newOs.quotivate.quotes.domain.entity.Quote

@Composable
fun MainScreen(navController: NavController, state: MainScreenState, onRefresh:()-> Unit, onFavoriteIconClick:(id:Int)-> Unit, onShareIconClick:(quote: Quote,context: Context)-> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(brush = gradientBackground),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        /* LocalQuote itself */
        DefaultTextView(
            text = state.quote.text,
            fontSize = 40.sp,
            textColor = white,
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxSize()
                .padding(4.dp, 4.dp, 4.dp, 1.dp),
            cornersShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        )

        /* LocalQuote author */
        DefaultTextView(
            text = state.quote.author,
            fontSize = 20.sp,
            textColor = white,
            backgroundColor = Color.Transparent,
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
                .padding(4.dp, 2.dp, 4.dp, 4.dp)
        ) {
            DefaultIconButton(
                imageId = com.newOs.quotivate.R.drawable.refresh_icon,
                imageDescription = "Refresh Button",
                borderColor = white,
                iconColor = marron,
                backgroundColor = white,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                onClick = { onRefresh() }
            )

            DefaultIconButton(
                imageId = com.newOs.quotivate.R.drawable.favorite_icon,
                imageDescription = "Favorite Button",
                borderColor = white,
                iconColor = marron,
                backgroundColor = white,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                onClick = { onFavoriteIconClick(state.quote.id)}
            )

            DefaultIconButton(
                imageId = com.newOs.quotivate.R.drawable.share_icon,
                imageDescription = "Share Button",
                borderColor = white,
                iconColor = marron,
                backgroundColor = white,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                onClick = { onShareIconClick(state.quote,context) }
            )

        }

        DefaultTextButton(
            text = "Favorites",
            modifier = Modifier
                .weight(0.14f)
                .fillMaxWidth()
                .padding(4.dp, 4.dp, 4.dp, 4.dp),
            textColor = marron,
            borderColor = white,
            backgroundColor = white,
            cornersShape = RoundedCornerShape(8.dp),
            onClick = { navController.navigate(Screen.FavoritesScreen.route) }
        )

        DefaultTextButton(
            text = "More",
            modifier = Modifier
                .weight(0.14f)
                .fillMaxWidth()
                .padding(4.dp, 4.dp, 4.dp, 8.dp),
            textColor = marron,
            borderColor = white,
            backgroundColor = white,
            cornersShape = RoundedCornerShape(8.dp),
            onClick = { navController.navigate(Screen.QuotesScreen.route) }
        )

    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize().padding(top=200.dp), contentAlignment = Alignment.TopCenter) {
            CircularProgressIndicator(color = white,modifier = Modifier.size(100.dp), strokeWidth = 12.dp)
        }
    }

    state.error?.let {
        Box(modifier = Modifier.fillMaxSize().padding(top=200.dp), contentAlignment = Alignment.TopCenter) {
            Text(text = it, color = white, fontSize = 30.sp)
        }
    }

}
