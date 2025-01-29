package com.kh.sbilyhour.composestructure.presentation.ui.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties

@Composable
fun InfoAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector?=null,
) {
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        icon = {
            if (icon != null) { // Conditionally render the icon only if it's not null
                Icon(icon, contentDescription = null)
            }
        },
        title = {
            Text(
                text = dialogTitle,
                modifier = Modifier.fillMaxWidth(), // Ensure it spans the full width
                textAlign = TextAlign.Start, // Align text to the start (left)
                style = MaterialTheme.typography.titleMedium // Use a title style
            )
        },
        text = {
            Text(
                text = dialogText,
                modifier = Modifier.fillMaxWidth(), // Ensure it spans the full width
                textAlign = TextAlign.Start, // Align text to the start (left)
                style = MaterialTheme.typography.bodyMedium // Use a body style
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("OK")
            }
        },

        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    )
}