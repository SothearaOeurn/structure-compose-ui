package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.SettingState
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.expandable.ExpandableListItem

@Composable
fun GeneralSettingsContent(state: SettingState) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "General Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(10) { index ->
            ExpandableListItem(
                title = "General Setting $index",
                detailsList = (state as? SettingState.DataListing)?.listing
            )
        }
    }
}

@Composable
fun SecuritySettingsContent(state: SettingState) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Security Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(5) { index ->
            ExpandableListItem(
                title = "Security Option $index",
                detailsList = (state as? SettingState.DataListing)?.listing
            )
        }
    }
}

@Composable
fun NotificationSettingsContent(state: SettingState) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Notification Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(8) { index ->
            ExpandableListItem(
                title = "Notification Preference $index",
                detailsList = (state as? SettingState.DataListing)?.listing
            )
        }
    }
}

@Composable
fun NFCSettingsContent(state: SettingState) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "NFC Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(8) { index ->
            ExpandableListItem(
                title = "NFC Preference $index",
                detailsList = (state as? SettingState.DataListing)?.listing
            )
        }
    }
}

@Composable
fun ProfileSettingsContent(state: SettingState) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Profile Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(8) { index ->
            ExpandableListItem(
                title = "Profile Preference $index",
                detailsList = (state as? SettingState.DataListing)?.listing
            )
        }
    }
}