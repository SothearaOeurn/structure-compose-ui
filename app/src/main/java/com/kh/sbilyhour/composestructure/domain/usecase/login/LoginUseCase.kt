package com.kh.sbilyhour.composestructure.domain.usecase.login

import com.kh.sbilyhour.composestructure.domain.Field
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
    /*fun validateCredentials(username: String, password: String): ValidationResult {
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
    }*/

    private val fixedUsername = "theara"  // Set fixed username
    private val fixedPassword = "1234567"  // Set fixed password

    fun validateCredentials(username: String, password: String): ValidationResult {
        return when {
            username != fixedUsername -> ValidationResult.Error(
                Field.Username,
                "Incorrect username"
            )

            password != fixedPassword -> ValidationResult.Error(
                Field.Password,
                "Incorrect password"
            )

            else -> ValidationResult.Success
        }
    }

    suspend fun execute(request: LoginRequest): BaseResponse<LoginResponse> {
        return try {
            if (request.username != fixedUsername || request.password != fixedPassword) {
                return BaseResponse(
                    statusCode = 401,
                    message = "Invalid username or password",
                    data = null
                )
            }

            // Simulate a successful login response (without calling API)
            val fakeResponse = LoginResponse(
                accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOaWhEVS1FV2JqbndYSTVTLU12ZW5CZGd3STF3MU5XWXY4Wk9lVHBIbmc4In0",
                expiresIn = 300.0,
                refreshExpiresIn = 1800.0,
                refreshToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiAoken",
                tokenType = "Bearer",
                notBeforePolicy = "",
                sessionState = "58b082cf-0c83-42f4-b29e-c8afedccf92c",
                scope = "profile email"
            )

            BaseResponse(statusCode = 200, message = "Login successful", data = fakeResponse)

        } catch (e: Exception) {
            BaseResponse(
                statusCode = 500,
                message = e.localizedMessage ?: "Login failed",
                data = null
            )
        }
    }
}
