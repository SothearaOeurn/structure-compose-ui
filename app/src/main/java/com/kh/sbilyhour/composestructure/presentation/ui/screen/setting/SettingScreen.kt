package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kh.sbilyhour.composestructure.presentation.ui.components.LoadingOverlay
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab.GeneralSettingsContent
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab.NFCSettingsContent
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab.NotificationSettingsContent
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab.ProfileSettingsContent
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab.SecuritySettingsContent
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.tablayout.ScrollableTabRowWidget

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ScrollableTabRowWidget(
                tabItems = viewModel.listing,
                modifier = Modifier.fillMaxWidth()
            ) { selectedIndex ->
                when (selectedIndex) {
                    0 -> GeneralSettingsContent(state)
                    1 -> SecuritySettingsContent(state)
                    2 -> NotificationSettingsContent(state)
                    3 -> NFCSettingsContent(state)
                    4 -> ProfileSettingsContent(state)
                }
            }
        }
        LoadingOverlay(isVisible = state is SettingState.Loading)
    }
}








