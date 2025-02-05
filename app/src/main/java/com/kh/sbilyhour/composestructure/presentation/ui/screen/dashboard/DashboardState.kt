package com.kh.sbilyhour.composestructure.presentation.ui.screen.dashboard

sealed class DashboardState {
    data object Idle : DashboardState()
    data object Loading : DashboardState()
    data class Error(val message: String) : DashboardState()
}