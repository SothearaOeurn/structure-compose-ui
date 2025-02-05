package com.kh.sbilyhour.composestructure.presentation.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kh.sbilyhour.composestructure.R
import com.kh.sbilyhour.composestructure.presentation.ui.navigation.AppScreen
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.dialogs.InformAlertDialog

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()
    var openAlertDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Image
            Surface(
                shape = CircleShape,
                shadowElevation = 4.dp,
                modifier = Modifier.size(120.dp)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.profile_placeholder),
                    contentDescription = stringResource(R.string.profile_picture),
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Information
            Text(
                text = user.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Help Button
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.help))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Logout Button
            Button(
                onClick = {
                    openAlertDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.logout))
            }
        }
    }
    // Alert Dialog
    if (openAlertDialog) {
        InformAlertDialog(
            onDismissRequest = {
                openAlertDialog = false
                viewModel.resetErrorState()
            },
            onConfirmation = {
                openAlertDialog = false
                viewModel.logout()
                viewModel.resetErrorState()
                navController.navigate(AppScreen.Login.route) {
                    popUpTo(AppScreen.Profile.route) { inclusive = true }
                }
            },
            dialogTitle = "Logout Confirmation",
            dialogText = "Are you sure you want to logout?",
            icon = null,
        )
    }
}

