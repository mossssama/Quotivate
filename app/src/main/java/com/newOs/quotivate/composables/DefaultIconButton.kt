package com.newOs.quotivate.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DefaultIconButton(imageId: Int, imageDescription: String, borderColor: Color, iconColor: Color, backgroundColor: Color, modifier: Modifier, onClick:()->Unit) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, borderColor),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Icon(
            painterResource(id = imageId),
            contentDescription = imageDescription,
            tint = iconColor,
        )
    }
}