package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.tablayout.TabLayoutItem

class TabSettingListing {
    fun listing() = listOf(
        TabLayoutItem("General", Icons.Default.Settings),
        TabLayoutItem("Security", Icons.Default.Lock),
        TabLayoutItem("Notifications", Icons.Default.Notifications),
        TabLayoutItem("NFC", Icons.Default.Nfc),
        TabLayoutItem("Profile", Icons.Default.Person),
    )
}