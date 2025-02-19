package com.kh.sbilyhour.composestructure.presentation.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.navigationbar.BottomNavItemWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userPreferences: UserPreferencesDataSource
) : ViewModel() {

    private val _title = MutableStateFlow(BottomNavItemWidget.Home.title)
    val title: StateFlow<String> = _title.asStateFlow()

    fun setTopAppBarTitle(value: String) {
        _title.value=value
    }
}