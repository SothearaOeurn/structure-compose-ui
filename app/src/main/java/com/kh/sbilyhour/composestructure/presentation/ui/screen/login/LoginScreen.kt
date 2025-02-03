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
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    var openErrorDialog = state is LoginState.Error

    LaunchedEffect(state) {
        when (state) {
            is LoginState.Error -> openErrorDialog = true
            is LoginState.Success -> navController.navigate("home")
            else -> Unit
        }
    }

    // Scaffold with TopAppBar for the Back Button
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text("Login Screen") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                val usernameError = (state as? LoginState.UserNameEmpty)?.message.orEmpty()
                val passwordError = (state as? LoginState.PasswordEmpty)?.message.orEmpty()

                TextFieldComponent(
                    value = username,
                    onValueChange = { viewModel.setUserName(it) },
                    style = TextFieldStyle(
                        label = stringResource(R.string.username), errorMessage = usernameError,
                        fontFamily = FontFamily.Serif
                    ),
                )

                Spacer(modifier = Modifier.height(8.dp))

                PasswordComponent(
                    value = password,
                    onValueChange = { viewModel.setPassword(it) },
                    label = stringResource(R.string.enter_your_password),
                    errorMessage = passwordError
                )

                Spacer(modifier = Modifier.height(16.dp))

                ButtonComponent(
                    text = "Login",
                    onClick = { viewModel.login(username, password) },
                    modifier = modifier.fillMaxWidth(),
                    style = ButtonStyle(
                        backgroundColor = Color.LightGray,
                        contentColor = Color.Yellow,
                        textColor = Color.Black,
                        cornerRadius = 16.dp,
                        height = 56.dp
                    )
                )
            }

            // Loading Overlay
            LoadingOverlay(isVisible = state is LoginState.Loading)

            // Alert Dialog
            if (openErrorDialog) {
                val errorMessage =
                    (state as? LoginState.Error)?.message ?: "Unknown error occurred"
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
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ComposeStructureTheme {
        LoginScreen(navController = rememberNavController())
    }
}