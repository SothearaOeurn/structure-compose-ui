package com.kh.sbilyhour.composestructure.presentation.ui.widgets.navigationbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavigationBarWidget(
    selectedTab: BottomNavItemWidget,
    onTabSelected: (BottomNavItemWidget, String) -> Unit
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        BottomNavItemWidget.entries.forEach { item ->
            NavigationBarItem(
                selected = item == selectedTab,
                onClick = { onTabSelected(item, item.title) }, // Pass item title
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}
