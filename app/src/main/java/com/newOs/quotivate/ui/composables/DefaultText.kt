package com.newOs.quotivate.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun DefaultText(text: String, fontSize: TextUnit, textColor: Color) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            color = textColor
        ),
        textAlign = TextAlign.Start,
    )
}