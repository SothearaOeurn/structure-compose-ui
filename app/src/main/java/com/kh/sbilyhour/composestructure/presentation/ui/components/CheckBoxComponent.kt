package com.kh.sbilyhour.composestructure.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CheckBoxStyle(
    val textColor: Color,
    val textStyle: TextStyle,
    val checkedColor: Color,
    val uncheckedColor: Color
)

@Composable
fun defaultCheckBoxStyle(): CheckBoxStyle {
    return CheckBoxStyle(
        textColor = MaterialTheme.colorScheme.onBackground,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            fontFamily = FontFamily.Serif
        ),
        checkedColor = MaterialTheme.colorScheme.primary,
        uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun CheckBoxComponent(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    style: CheckBoxStyle = defaultCheckBoxStyle() // Default style
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            colors = CheckboxDefaults.colors(
                checkedColor = style.checkedColor,
                uncheckedColor = style.uncheckedColor
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            color = style.textColor,
            style = style.textStyle
        )
    }
}

