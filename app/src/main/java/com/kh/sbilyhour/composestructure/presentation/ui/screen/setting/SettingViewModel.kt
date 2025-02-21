package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh.sbilyhour.composestructure.presentation.ui.screen.setting.tab.TabSettingListing
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.tablayout.TabLayoutItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<SettingState>(SettingState.Idle)
    val state: StateFlow<SettingState> = _state.asStateFlow()

    val listing : List<TabLayoutItem> = TabSettingListing().listing()

    init {
        getListing()
    }

    private fun getListing(){
        viewModelScope.launch {
            _state.emit(SettingState.Loading)
            delay(500) // Add a delay of 500ms before fetching the listing
            val listing = SettingListing().listing()
            _state.emit(SettingState.DataListing(listing))
        }
    }
}