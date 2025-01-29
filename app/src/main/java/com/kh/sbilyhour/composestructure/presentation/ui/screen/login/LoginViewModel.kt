package com.kh.sbilyhour.composestructure.presentation.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh.sbilyhour.composestructure.domain.Field
import com.kh.sbilyhour.composestructure.domain.ValidationResult
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) {
        // First validate the credentials using the validation logic
        _loginState.value = LoginState.Loading
        val validationResult = loginUseCase.validateCredentials(username, password)

        if (validationResult is ValidationResult.Error) {
            _loginState.value = when (validationResult.field) {
                Field.Username -> LoginState.UserNameEmpty(validationResult.message)
                Field.Password -> LoginState.PasswordEmpty(validationResult.message)
            }
            return
        }
        // If validation passes, proceed with the login
        viewModelScope.launch {
            val request=LoginRequest(username,password)
            val loginEntity = loginUseCase.execute(request)
            when (loginEntity.statusCode) {
                200 -> {
                    loginEntity.data?.let {
                        _loginState.value = LoginState.Success(it)
                    }
                }

                else -> _loginState.value = LoginState.Error(loginEntity.message)
            }
        }
    }

    fun resetErrorState() {
        _loginState.value = LoginState.Idle
    }
}