package com.kh.sbilyhour.composestructure.presentation.ui.screen.detail

sealed class DetailState {
    data object Idle : DetailState()
    data object Loading : DetailState()
    data class Error(val message: String) : DetailState()
}