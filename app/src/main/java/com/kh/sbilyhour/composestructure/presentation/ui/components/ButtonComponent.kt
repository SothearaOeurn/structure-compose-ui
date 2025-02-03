package com.kh.sbilyhour.composestructure.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class ButtonStyle(
    val backgroundColor: Color = Color.Blue,
    val contentColor: Color = Color.White,
    val textColor: Color = contentColor,
    val fontFamily: FontFamily = FontFamily.Default,
    val cornerRadius: Dp = 8.dp,
    val height: Dp = 48.dp
)

@Composable
fun ButtonComponent(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = ButtonStyle(),
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(style.height)
            .then(modifier),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = style.backgroundColor, contentColor = style.contentColor
        ),
        shape = RoundedCornerShape(style.cornerRadius)
    ) {
        icon?.let {
            Icon(it, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
        }
        Text(
            text = text, color = style.textColor, fontFamily = style.fontFamily
        )
    }
}

