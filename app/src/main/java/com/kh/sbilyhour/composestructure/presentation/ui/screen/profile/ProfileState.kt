package com.kh.sbilyhour.composestructure.presentation.ui.screen.profile

sealed class ProfileState {
    data object Idle : ProfileState()
    data object Loading : ProfileState()
    data class Error(val message: String) : ProfileState()
}