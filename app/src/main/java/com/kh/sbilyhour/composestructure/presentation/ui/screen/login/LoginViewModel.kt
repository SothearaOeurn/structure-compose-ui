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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.emit(LoginState.Loading)
            when (val validationResult = loginUseCase.validateCredentials(username, password)) {
                is ValidationResult.Error -> {
                    when (validationResult.field) {
                        Field.Username -> _state.emit(LoginState.UserNameEmpty(validationResult.message))
                        Field.Password -> _state.emit(LoginState.PasswordEmpty(validationResult.message))
                        else -> Unit
                    }
                    return@launch
                }

                ValidationResult.Success -> {
                    runCatching {
                        val request = LoginRequest(username, password)
                        loginUseCase.execute(request)
                    }.onSuccess { response ->
                        _state.emit(
                            when (response.statusCode) {
                                200 -> response.data?.let { LoginState.Success(it) }
                                    ?: LoginState.Error("Invalid response")
                                else -> LoginState.Error(response.message)
                            }
                        )
                    }.onFailure { e ->
                        _state.emit(LoginState.Error(e.localizedMessage ?: "Unknown error occurred"))
                    }
                }
            }
        }
    }

    fun setUserName(value: String) {
        _username.value=value
    }

    fun setPassword(value: String) {
        _password.value=value
    }

    fun resetErrorState() {
        viewModelScope.launch { _state.emit(LoginState.Idle) }
    }
}
