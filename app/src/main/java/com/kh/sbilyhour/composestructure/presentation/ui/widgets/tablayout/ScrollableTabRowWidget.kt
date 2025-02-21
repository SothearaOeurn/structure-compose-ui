package com.kh.sbilyhour.composestructure.presentation.ui.widgets.tablayout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableTabRowWidget(
    tabItems: List<TabLayoutItem>?,
    modifier: Modifier = Modifier,
    content: @Composable (selectedIndex: Int) -> Unit
) {
    val selectedTabIndex = rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Wrap the ScrollableTabRow in a Row (optional if you want to customize)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ScrollableTabRow(
                contentColor = MaterialTheme.colorScheme.primary,
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth(),
                edgePadding = 16.dp,
                indicator = { tabPositions ->
                    if (tabPositions.isNotEmpty()) {
                        TabRowDefaults.Indicator(
                            modifier = Modifier
                                .fillMaxWidth() // Ensures the indicator spans the entire width
                                .tabIndicatorOffset(tabPositions[selectedTabIndex.value]), // Keeps it under the selected tab
                            color = MaterialTheme.colorScheme.primary,
                            height = 2.dp // Adjust the height of the indicator
                        )
                    }
                }
            ) {
                tabItems?.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        onClick = { selectedTabIndex.value = index },
                        icon = { Icon(tabItem.icon, contentDescription = tabItem.title) },
                        text = { Text(tabItem.title) }
                    )
                }
            }
        }

        // Content based on selected tab
        content(selectedTabIndex.value)
    }
}
