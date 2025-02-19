package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting

import com.kh.sbilyhour.composestructure.presentation.ui.widgets.expandable.Section

sealed class SettingState {
    data object Idle : SettingState()
    data object Loading : SettingState()
    data class DataListing(val listing: List<Section>) : SettingState()
}