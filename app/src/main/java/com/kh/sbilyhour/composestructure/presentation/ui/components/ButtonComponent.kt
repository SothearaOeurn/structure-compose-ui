package com.kh.sbilyhour.composestructure.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonComponent(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Blue,
    contentColor: Color = Color.White, // Button's general content color (text & icon)
    textColor: Color = contentColor,  // Custom text color
    icon: ImageVector? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    fontFamily: FontFamily = FontFamily.Default, // Default font family
    cornerRadius: Dp = 8.dp, // Default corner radius
    height: Dp = 48.dp // Default height of the button
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(height) // Set button height
            .then(modifier), // Ensure other modifiers are retained
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(cornerRadius) // Apply the corner radius
    ) {
        // Icon if provided
        icon?.let {
            Icon(it, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
        }

        // Text with custom fontFamily
        Text(
            text = text,
            color = textColor,
            fontFamily = fontFamily // Apply the font family to the text
        )
    }
}
