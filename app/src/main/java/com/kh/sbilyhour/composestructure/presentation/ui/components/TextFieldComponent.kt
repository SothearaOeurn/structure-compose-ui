package com.kh.sbilyhour.composestructure.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

data class TextFieldStyle(
    val label: String = "Text Field",
    val errorMessage: String = "",
    val isPassword: Boolean = false,
    val fontFamily: FontFamily = FontFamily.Default
)

@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    style: TextFieldStyle = TextFieldStyle(), // Grouped parameters
    icon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    // Outlined TextField with icons and password toggle functionality
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(style.label, fontFamily = style.fontFamily) }, // Apply font family to label
        isError = style.errorMessage.isNotEmpty(),
        visualTransformation = if (style.isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (style.isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        },
        leadingIcon = icon, // Add custom leading icon if provided
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (style.isPassword) KeyboardType.Password else KeyboardType.Text)
    )

    // Error message
    if (style.errorMessage.isNotEmpty()) {
        Text(
            text = style.errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall.copy(fontFamily = style.fontFamily), // Apply font family to error message
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}

