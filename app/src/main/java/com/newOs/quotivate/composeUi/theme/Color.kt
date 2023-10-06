package com.newOs.quotivate.composeUi.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

/* Notes colors */
val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)
val marron = Color(0xFFff455d)
val dark_marron = Color(0xFFC08080)

val gradientBackground = Brush.linearGradient(
    colors = listOf(
        dark_marron,
        marron,
    )
)