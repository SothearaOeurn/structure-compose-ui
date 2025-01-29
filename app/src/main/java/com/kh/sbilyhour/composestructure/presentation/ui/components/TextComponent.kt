package com.kh.sbilyhour.composestructure.presentation.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

@Composable
fun TextComponent(
    text: String,
    fontFamily: FontFamily = FontFamily.Default, // Custom font family (default is FontFamily.Default)
    textColor: Color = Color.Black, // Custom text color
    style: TextStyle = MaterialTheme.typography.body1, // Default style
    modifier: Modifier = Modifier // Modifier to apply to the Text component
) {
    Text(
        text = text,
        color = textColor,
        style = style.copy(fontFamily = fontFamily), // Apply the font family to the style
        modifier = modifier
    )
}