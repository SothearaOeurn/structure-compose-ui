package com.kh.sbilyhour.composestructure.presentation.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kh.sbilyhour.composestructure.presentation.ui.components.BottomSheetComponent
import com.kh.sbilyhour.composestructure.presentation.ui.components.CheckBoxComponent
import com.kh.sbilyhour.composestructure.presentation.ui.components.RadioButtonComponent
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.dialogs.InformAlertDialog

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val username by viewModel.username.collectAsState()
    var openAlertDialog by remember { mutableStateOf(false) }
    var showSheet by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = "Home Screen") },
                actions = {
                    IconButton(onClick = { navController.navigate("login") }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "More Options"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(text = "Access Token:${username}", maxLines = 3)
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    CardItem("Production Listing", onClick = {})
                    CardItem("Category Listing", onClick = {})
                    CardItem("Create Production", onClick = {})
                    CardItem("Create Category", onClick = {  })
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    CardItem("Login", onClick = { navController.navigate("login") })
                    CardItem("Register", onClick = { navController.navigate("register") })
                    CardItem("Logout", onClick = { openAlertDialog = true })
                    CardItem("Button Sheet", onClick = { showSheet = true })
                }

            }

            Row {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    CheckBoxComponent(
                        text = "CheckBox",
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                    RadioButtonComponent(
                        selected = selectedOption,
                        onSelect = { selectedOption = it },
                        text = "Radio Button",
                        selectedColor = MaterialTheme.colorScheme.secondary,
                        unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
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
            },
            dialogTitle = "Logout Confirmation",
            dialogText = "Are you sure you want to logout?",
            icon = null,
        )
    }
    if (showSheet) {
        BottomSheetComponent(onDismiss = { showSheet = false }) {
            Text("Dynamic Content Here!", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { showSheet = false }) {
                Text("Dismiss")
            }
        }
    }
}


@Composable
fun CardItem(text: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.Gray),
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(90.dp)
            .clickable { onClick() } // Adding click handler
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.Black)
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen(navController = rememberNavController())
}