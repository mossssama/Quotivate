package com.newOs.quotivate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.newOs.quotivate.composables.TxtView
import com.newOs.quotivate.ui.theme.baby_blue
import com.newOs.quotivate.ui.theme.black

@Composable
fun QuotesScreen(name:String?) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        QText(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(4.dp,4.dp,4.dp,1.dp)
        )
        QText(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(4.dp,4.dp,4.dp,1.dp)
        )
    }
}

@Composable
fun QText(modifier: Modifier) {
    TxtView(text = "QuotesScreen",fontSize = 80.sp,textColor = black, backgroundColor = baby_blue,modifier = modifier, cornersShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
}