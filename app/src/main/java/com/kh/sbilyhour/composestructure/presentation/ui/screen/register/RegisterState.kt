package com.kh.sbilyhour.composestructure.presentation.ui.screen.register

import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data class UserNameEmpty(val message: String) : RegisterState()
    data class EmailEmpty(val message: String) : RegisterState()
    data class PasswordEmpty(val message: String) : RegisterState()
    data class Success(val loginEntity: LoginResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
}