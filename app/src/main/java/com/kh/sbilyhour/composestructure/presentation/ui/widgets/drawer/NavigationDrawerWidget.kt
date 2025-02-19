import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun NavigationDrawerWidget(
    drawerState: DrawerState,
    drawerContent: @Composable () -> Unit, // Dynamic drawer content
    content: @Composable (PaddingValues) -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = drawerContent, // Use dynamic content
        drawerState = drawerState
    ) {
        Scaffold(
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}


