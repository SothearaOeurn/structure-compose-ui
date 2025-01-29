package com.kh.sbilyhour.composestructure.presentation.ui.screen.login

import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class UserNameEmpty(val message: String) : LoginState()
    data class PasswordEmpty(val message: String) : LoginState()
    data class Success(val loginEntity: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}