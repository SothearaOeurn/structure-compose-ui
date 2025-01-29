package com.kh.sbilyhour.composestructure.domain.usecase.login

import com.kh.sbilyhour.composestructure.domain.ValidationResult
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import com.kh.sbilyhour.composestructure.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository,private val validation: LoginValidation
) {
    // Perform the validation of the credentials and return any validation errors
    fun validateCredentials(username: String, password: String): ValidationResult {
        val usernameResult = validation.validateUsername(username)
        if (usernameResult is ValidationResult.Error) return usernameResult

        val passwordResult = validation.validatePassword(password)
        if (passwordResult is ValidationResult.Error) return passwordResult

        return ValidationResult.Success
    }

    suspend fun execute(request: LoginRequest): BaseResponse<LoginResponse> {
        // Perform validation before calling the repository
        return loginRepository.login(request)
    }
}