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

@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Text Field",
    errorMessage: String = "",
    icon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = FontFamily.Default // Default font family
) {
    var passwordVisible by remember { mutableStateOf(false) }

    // Outlined TextField with icons and password toggle functionality
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontFamily = fontFamily) }, // Apply font family to label
        isError = errorMessage.isNotEmpty(),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
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
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text)
    )

    // Error message
    if (errorMessage.isNotEmpty()) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall.copy(fontFamily = fontFamily), // Apply font family to error message
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}
