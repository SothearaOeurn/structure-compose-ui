import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import com.kh.sbilyhour.composestructure.presentation.ui.navigation.AppScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.dashboard.DashboardScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.detail.DetailScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.home.HomeScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.profile.ProfileScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.login.LoginScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.register.RegisterScreen
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.SettingScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    userPreferencesDataSource: UserPreferencesDataSource
) {
    val isUserLoggedInState = userPreferencesDataSource.isUserLoggedInFlow.collectAsState(initial = null)

    when (val isUserLoggedIn = isUserLoggedInState.value) {
        null -> LoadingScreen() // Show loading UI
        else -> {
            LaunchedEffect(isUserLoggedIn) {
                if (isUserLoggedIn) {
                    navController.navigate(AppScreen.Dashboard.route) {
                        popUpTo(AppScreen.Login.route) { inclusive = true }
                    }
                }
            }

            AnimatedNavHost(
                navController = navController,
                startDestination = if (isUserLoggedIn) AppScreen.Dashboard.route else AppScreen.Login.route,
                modifier = modifier.fillMaxSize(),
                enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) }
            ) {
                composable(AppScreen.Login.route) {
                    LoginScreen(navController = navController)
                }
                composable(AppScreen.Home.route) {
                    HomeScreen(navController = navController)
                }
                composable(AppScreen.Register.route) {
                    RegisterScreen(navController = navController)
                }
                composable(AppScreen.Dashboard.route) {
                    DashboardScreen(navController = navController)
                }
                composable(AppScreen.Profile.route) {
                    ProfileScreen(navController = navController)
                }
                composable(AppScreen.Detail.route) {
                    DetailScreen(navController = navController)
                }
                composable(AppScreen.Setting.route) {
                    SettingScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    androidx.compose.material3.CircularProgressIndicator()
}

