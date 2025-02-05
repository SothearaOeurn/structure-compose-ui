package com.kh.sbilyhour.composestructure.presentation.ui.navigation

sealed class AppScreen(val route: String) {
    data object Login : AppScreen("login")
    data object Home : AppScreen("home")
    data object Register : AppScreen("register")
    data object Dashboard : AppScreen("dashboard")
    data object Profile : AppScreen("profile")
    data object Detail : AppScreen("detail")
}