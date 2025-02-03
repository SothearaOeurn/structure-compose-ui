package com.kh.sbilyhour.composestructure.presentation.ui.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kh.sbilyhour.composestructure.R
import com.kh.sbilyhour.composestructure.presentation.ui.components.ButtonComponent
import com.kh.sbilyhour.composestructure.presentation.ui.components.ButtonStyle
import com.kh.sbilyhour.composestructure.presentation.ui.components.LoadingOverlay
import com.kh.sbilyhour.composestructure.presentation.ui.components.PasswordComponent
import com.kh.sbilyhour.composestructure.presentation.ui.components.TextFieldComponent
import com.kh.sbilyhour.composestructure.presentation.ui.components.TextFieldStyle
import com.kh.sbilyhour.composestructure.presentation.ui.dialogs.InfoAlertDialog
import com.kh.sbilyhour.composestructure.presentation.ui.theme.ComposeStructureTheme

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val email by viewModel.email.collectAsState()

    var openErrorDialog = state is RegisterState.Error
    var openSuccessDialog = state is RegisterState.Success

    LaunchedEffect(state) {
        when (state) {
            is RegisterState.Error -> openErrorDialog = true
            is RegisterState.Success -> openSuccessDialog = true
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.surface,
                title = { Text("Register Screen") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val usernameError = (state as? RegisterState.UserNameEmpty)?.message.orEmpty()
            val passwordError = (state as? RegisterState.PasswordEmpty)?.message.orEmpty()
            val emailError = (state as? RegisterState.EmailEmpty)?.message.orEmpty()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldComponent(
                    value = username,
                    onValueChange = { viewModel.setUsername(it) },
                    style = TextFieldStyle(
                        label = stringResource(R.string.username), errorMessage = usernameError,
                        fontFamily = FontFamily.Serif
                    ),

                    )
                Spacer(modifier = Modifier.height(16.dp))

                TextFieldComponent(
                    value = email,
                    onValueChange = { viewModel.setEmail(it) },
                    style = TextFieldStyle(
                        label = stringResource(R.string.email), errorMessage = emailError,
                        fontFamily = FontFamily.Serif
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))

                PasswordComponent(
                    value = password,
                    onValueChange = { viewModel.setPassword(it) },
                    label = stringResource(R.string.enter_your_password),
                    errorMessage = passwordError
                )
                Spacer(modifier = Modifier.height(24.dp))

                ButtonComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.register),
                    onClick = { viewModel.requestRegister(username, email, password) },
                    style = ButtonStyle(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        textColor = MaterialTheme.colorScheme.onPrimary,
                        cornerRadius = 16.dp,
                        height = 56.dp
                    )

                )
            }
            // Loading Overlay
            LoadingOverlay(isVisible = state is RegisterState.Loading)
        }
    }

    // Alert Dialog
    if (openErrorDialog) {
        val errorMessage =
            (state as? RegisterState.Error)?.message
                ?: stringResource(R.string.unknown_error_occurred)
        InfoAlertDialog(
            onDismissRequest = {
                openErrorDialog = false
                viewModel.resetErrorState()
            },
            onConfirmation = {
                openErrorDialog = false
                viewModel.resetErrorState()
            },
            dialogTitle = "Login Error",
            dialogText = errorMessage,
            icon = null,
        )
    }

    if (openSuccessDialog) {
        InfoAlertDialog(
            onDismissRequest = {
                openSuccessDialog = false
                viewModel.resetErrorState()
            },
            onConfirmation = {
                openSuccessDialog = false
                viewModel.resetErrorState()
                navController.popBackStack(route = "home", inclusive = false)
            },
            dialogTitle = stringResource(R.string.register_successful),
            dialogText = stringResource(R.string.after_click_okay_button_you_will_be_redirected_to_home_screen_please_go_to_login),
            icon = null,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ComposeStructureTheme {
        RegisterScreen(navController = rememberNavController())
    }
}