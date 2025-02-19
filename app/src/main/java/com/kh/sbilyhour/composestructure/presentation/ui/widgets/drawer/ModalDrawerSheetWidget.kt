package com.kh.sbilyhour.composestructure.presentation.ui.widgets.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ModelDrawerSheetWidget(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            // Header
            DrawerHeader("Theara", "", "00000002")

            HorizontalDivider()

            // Drawer items
            DrawerItem("Home", Icons.Default.Home, onClick = {
                coroutineScope.launch {
                    drawerState.close()
                }
            })
            DrawerItem("Stocks", Icons.Default.ShoppingCart, onClick = {
                coroutineScope.launch {
                    drawerState.close()
                }
            })
            DrawerItem("Profile", Icons.Default.Person, onClick = {
                coroutineScope.launch {
                    drawerState.close()
                }
            })
            DrawerItem("Settings", Icons.Default.Settings, onClick = {
                coroutineScope.launch {
                    drawerState.close()
                }
            })

            Spacer(Modifier.weight(1f))

            HorizontalDivider()
            // Footer
            DrawerFooter()
        }
    }
}

@Composable
fun DrawerHeader(username: String, profile: String, userId: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = username,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = profile)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "ID: $userId")
    }
}

@Composable
fun DrawerFooter() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f)) // Spacer to push footer to the bottom
         Text(
            text = "Make 2015",
             textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun DrawerItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(title) },
        selected = false,
        icon = { Icon(icon, contentDescription = null) },
        onClick = {
            onClick()
        }
    )
}



