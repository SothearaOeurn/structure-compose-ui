package com.kh.sbilyhour.composestructure.presentation.ui.screen.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kh.sbilyhour.composestructure.R
import com.kh.sbilyhour.composestructure.presentation.ui.components.*
import com.kh.sbilyhour.composestructure.presentation.ui.navigation.AppScreen
import com.kh.sbilyhour.composestructure.presentation.ui.theme.ComposeStructureTheme
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.dialogs.InformAlertDialog

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    var openErrorDialog by remember { mutableStateOf(state is LoginState.Error) }

    LaunchedEffect(state) {
        when (state) {
            is LoginState.Error -> openErrorDialog = true
            is LoginState.Success -> navController.navigate(AppScreen.Dashboard.route)
            else -> Unit
        }
    }

    Scaffold(
        topBar = {},
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,

                ) {
                val usernameError = (state as? LoginState.UserNameEmpty)?.message.orEmpty()
                val passwordError = (state as? LoginState.PasswordEmpty)?.message.orEmpty()

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.stock),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextFieldComponent(
                    value = username,
                    onValueChange = { viewModel.setUserName(it) },
                    style = TextFieldStyle(
                        label = stringResource(R.string.username),
                        errorMessage = usernameError,
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
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login",
                    onClick = { viewModel.login(username, password) },
                    style = ButtonStyle(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        textColor = MaterialTheme.colorScheme.onPrimary,
                        cornerRadius = 16.dp,
                        height = 56.dp
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextComponent(
                    text = "Don't have an account? Click here to register",
                    textColor = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Serif
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(AppScreen.Register.route) }
                        .padding(8.dp)
                )
            }

            // Loading Overlay
            LoadingOverlay(isVisible = state is LoginState.Loading)

            // Error Dialog
            if (openErrorDialog) {
                val errorMessage = (state as? LoginState.Error)?.message ?: "Unknown error occurred"
                InformAlertDialog(
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
