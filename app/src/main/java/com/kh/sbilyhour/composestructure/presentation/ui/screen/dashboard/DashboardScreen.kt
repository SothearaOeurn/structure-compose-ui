package com.kh.sbilyhour.composestructure.presentation.ui.screen.dashboard

import NavigationDrawerWidget
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
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.drawer.ModelDrawerSheetWidget
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.navigationbar.BottomNavItemWidget
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.navigationbar.NavigationBarWidget
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel = hiltViewModel()) {
    var selectedTab by remember { mutableStateOf(BottomNavItemWidget.Home) }
    val title by viewModel.title.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationDrawerWidget(
        drawerState = drawerState,
        drawerContent = {
            ModelDrawerSheetWidget(drawerState = drawerState)
        }
    ) { paddingValues ->
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(title, fontWeight = FontWeight.Bold) },
                    actions = {
                        IconButton(onClick = { /* Refresh Data */ }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            // Toggle drawer state (open/close)
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
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
                AnimatedVisibility(visible = selectedTab == BottomNavItemWidget.Home) {
                    HomeScreen(navController = navController)
                }
                AnimatedVisibility(visible = selectedTab == BottomNavItemWidget.Stocks) {
                    StockScreen()
                }
                AnimatedVisibility(visible = selectedTab == BottomNavItemWidget.Profile) {
                    ProfileScreen(navController = navController)
                }
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
