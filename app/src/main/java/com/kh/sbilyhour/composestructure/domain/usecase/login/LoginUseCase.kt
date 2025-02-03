package com.kh.sbilyhour.composestructure.domain.usecase.login

import com.kh.sbilyhour.composestructure.domain.ValidationResult
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import com.kh.sbilyhour.composestructure.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val validation: LoginValidation
) {
    fun validateCredentials(username: String, password: String): ValidationResult {
        return when {
            validation.validateUsername(username) is ValidationResult.Error -> validation.validateUsername(username)
            validation.validatePassword(password) is ValidationResult.Error -> validation.validatePassword(password)
            else -> ValidationResult.Success
        }
    }


    suspend fun execute(request: LoginRequest): BaseResponse<LoginResponse> {
        return try {
            loginRepository.login(request)
        } catch (e: Exception) {
            BaseResponse(statusCode = 500, message = e.localizedMessage ?: "Login failed",data = null)
        }
    }

}
