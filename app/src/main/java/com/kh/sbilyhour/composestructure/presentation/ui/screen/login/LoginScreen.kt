package com.kh.sbilyhour.composestructure.presentation.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kh.sbilyhour.composestructure.presentation.ui.components.ButtonComponent
import com.kh.sbilyhour.composestructure.presentation.ui.components.PasswordComponent
import com.kh.sbilyhour.composestructure.presentation.ui.components.TextFieldComponent
import com.kh.sbilyhour.composestructure.presentation.ui.dialogs.ConfirmAlertDialog
import com.kh.sbilyhour.composestructure.presentation.ui.theme.ComposeStructureTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val viewModel: LoginViewModel = hiltViewModel()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()
    var openAlertDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            val usernameError = (loginState as? LoginState.UserNameEmpty)?.message.orEmpty()
            val passwordError = (loginState as? LoginState.PasswordEmpty)?.message.orEmpty()

            TextFieldComponent(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                errorMessage = usernameError,
                icon = {
                    Icon(Icons.Default.Person, contentDescription = "Username Icon")
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordComponent(
                value = password,
                onValueChange = { input ->
                    password = input
                },
                label = "Enter your password",
                errorMessage = passwordError
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(
                text = "Login",
                onClick = { viewModel.login(username,password) },
                backgroundColor = Color.Gray,
                textColor = Color.White,
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 10.dp, // Apply custom corner radius here
                height = 50.dp, // Set custom height here
                fontFamily = FontFamily.SansSerif,
            )
        }

        // Alert Dialog
        if (openAlertDialog) {
            val errorMessage =
                (loginState as? LoginState.Error)?.message ?: "Unknown error occurred"
            ConfirmAlertDialog(
                onDismissRequest = {
                    openAlertDialog = false
                    viewModel.resetErrorState()
                },
                onConfirmation = {
                    openAlertDialog = false
                    viewModel.resetErrorState()
                },
                dialogTitle = "Login Error",
                dialogText = errorMessage,
                icon = null,
            )
        }

        // Loading Overlay
        LoadingOverlay(isVisible = loginState is LoginState.Loading)

        // Success or Error Message
        when (val state = loginState) {
            is LoginState.Success -> Text(
                "Login Successful! Welcome\nToken: ${state.loginEntity.accessToken?.substring(0,100)}",
                modifier = Modifier.align(Alignment.TopCenter)
            )

            is LoginState.Error -> {
                openAlertDialog = true
            }

            else -> Unit
        }
    }

    // Observe state changes and prevent multiple dialog openings
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Error) {
            openAlertDialog = true
        }
    }
}

@Composable
fun LoadingOverlay(isVisible: Boolean) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ComposeStructureTheme {
        LoginScreen()
    }
}