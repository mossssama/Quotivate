package com.newOs.quotivate.composables

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextBtn(text: String, modifier: Modifier, textColor: Color, backgroundColor: Color, cornersShape: Shape, onClick: ()-> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = modifier,
        shape = cornersShape
    ) {
        Text(text = text,color = textColor, style = TextStyle(fontSize = 30.sp))
    }
}
