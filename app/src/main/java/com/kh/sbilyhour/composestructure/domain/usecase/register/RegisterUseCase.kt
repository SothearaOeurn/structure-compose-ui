package com.kh.sbilyhour.composestructure.domain.usecase.register

import com.kh.sbilyhour.composestructure.domain.ValidationResult
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import com.kh.sbilyhour.composestructure.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val validation: RegisterValidation
) {
    fun validateCredentials(username: String,email: String, password: String): ValidationResult {
        return when {
            validation.validateUsername(username) is ValidationResult.Error -> validation.validateUsername(username)
            validation.validateEmail(email) is ValidationResult.Error -> validation.validateEmail(email)
            validation.validatePassword(password) is ValidationResult.Error -> validation.validatePassword(password)
            else -> ValidationResult.Success
        }
    }

    suspend fun execute(request: RegisterRequest): BaseResponse<LoginResponse> {
        return try {
            registerRepository.register(request)
        } catch (e: Exception) {
            BaseResponse(statusCode = 500, message = e.localizedMessage ?: "Register failed",data = null)
        }
    }
}
