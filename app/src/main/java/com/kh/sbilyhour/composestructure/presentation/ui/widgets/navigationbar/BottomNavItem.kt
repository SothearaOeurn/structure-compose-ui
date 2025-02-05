package com.kh.sbilyhour.composestructure.presentation.ui.widgets.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person

// 🚀 Bottom Navigation Items
enum class BottomNavItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {

    Home("Home", Icons.Default.Home),
    Stocks("Stocks", Icons.AutoMirrored.Filled.ShowChart),
    Profile("Profile", Icons.Default.Person);
}