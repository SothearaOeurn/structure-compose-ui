package com.kh.sbilyhour.composestructure.presentation.ui.screen.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.kh.sbilyhour.composestructure.presentation.ui.navigation.AppScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.home.HomeScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.profile.ProfileScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.stock.StockScreen
import com.kh.sbilyhour.composestructure.presentation.ui.theme.ComposeStructureTheme
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.navigationbar.BottomNavItem
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.navigationbar.NavigationBarWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController,viewModel: DashboardViewModel = hiltViewModel()) {
    var selectedTab by remember { mutableStateOf(BottomNavItem.Home) }
    val title by viewModel.title.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* Refresh Data */ }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AppScreen.Setting.route) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Stock")
            }
        },
        bottomBar = {
            NavigationBarWidget(selectedTab) { newTab, newTitle ->
                selectedTab = newTab
                viewModel.setTopAppBarTitle(newTitle)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AnimatedVisibility(visible = selectedTab == BottomNavItem.Home) {
                HomeScreen(navController = navController)
            }
            AnimatedVisibility(visible = selectedTab == BottomNavItem.Stocks) {
                StockScreen()
            }
            AnimatedVisibility(visible = selectedTab == BottomNavItem.Profile) {
                ProfileScreen(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardStockScreenPreview() {
    ComposeStructureTheme {
        DashboardScreen(navController = rememberNavController())
    }
}
