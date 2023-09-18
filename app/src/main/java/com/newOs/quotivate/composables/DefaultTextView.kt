package com.newOs.quotivate.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTextView(text: String, fontSize: TextUnit, textColor: Color, backgroundColor: Color, modifier: Modifier, cornersShape: Shape= RoundedCornerShape(topStart = 0.dp)) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor,shape = cornersShape)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize,
                color = textColor
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
