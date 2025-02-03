package com.kh.sbilyhour.composestructure.presentation.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh.sbilyhour.composestructure.domain.Field
import com.kh.sbilyhour.composestructure.domain.ValidationResult
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.usecase.register.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun requestRegister(username: String, email: String, password: String) {
        viewModelScope.launch {
            _state.emit(RegisterState.Loading)

            when (val validationResult = registerUseCase.validateCredentials(username, email, password)) {
                is ValidationResult.Error -> {
                    val errorState = when (validationResult.field) {
                        Field.Username -> RegisterState.UserNameEmpty(validationResult.message)
                        Field.Password -> RegisterState.PasswordEmpty(validationResult.message)
                        Field.Email -> RegisterState.EmailEmpty(validationResult.message)
                    }
                    _state.emit(errorState)
                }

                ValidationResult.Success -> {
                    runCatching {
                        val request = RegisterRequest(username, email, password)
                        registerUseCase.execute(request)
                    }.onSuccess { response ->
                        _state.emit(
                            when (response.statusCode) {
                                200 -> response.data?.let { RegisterState.Success(it) }
                                    ?: RegisterState.Error("Invalid response")
                                else -> RegisterState.Error(response.message)
                            }
                        )
                    }.onFailure { e ->
                        _state.emit(RegisterState.Error(e.localizedMessage ?: "Unknown error occurred"))
                    }
                }
            }
        }
    }
    fun setUsername(value: String) {
        _username.value = value
    }

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun resetErrorState() {
        viewModelScope.launch { _state.emit(RegisterState.Idle) }
    }
}