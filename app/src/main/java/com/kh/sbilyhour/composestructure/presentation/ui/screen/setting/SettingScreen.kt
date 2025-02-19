package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kh.sbilyhour.composestructure.presentation.ui.components.LoadingOverlay
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.expandable.ExpandableListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Use LazyColumn to handle scrollable content
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize() // Ensure the LazyColumn fills the screen
                .padding(paddingValues)
        ) {
            // Add header as a separate item
            item {
                Text(
                    text = "Header Section",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // List of ExpandableItems
            items(30) { index ->
                ExpandableListItem(
                    title = "I. Loan Account information $index",
                    detailsList = (state as? SettingState.DataListing)?.listing
                )
            }
        }
        LoadingOverlay(isVisible = state is SettingState.Loading)
    }
}






