package com.kh.sbilyhour.composestructure.presentation.ui.screen.home

sealed class HomeState {
    data object Idle : HomeState()
    data object Loading : HomeState()
    data class Error(val message: String) : HomeState()
}