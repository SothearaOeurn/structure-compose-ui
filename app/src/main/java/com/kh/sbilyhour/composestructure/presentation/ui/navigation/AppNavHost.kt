package com.kh.sbilyhour.composestructure.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kh.sbilyhour.composestructure.presentation.ui.screen.login.LoginScreen
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kh.sbilyhour.composestructure.presentation.ui.screen.home.HomeScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.register.RegisterScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier.fillMaxSize()
    ) {
        composable("login") {
            LoginScreen(
                navController = navController
            )
        }
        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("register") {
            RegisterScreen(navController = navController)
        }
    }
}

