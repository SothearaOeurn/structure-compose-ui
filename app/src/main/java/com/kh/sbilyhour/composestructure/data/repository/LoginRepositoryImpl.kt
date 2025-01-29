package com.kh.sbilyhour.composestructure.data.repository


import com.kh.sbilyhour.composestructure.data.mapper.LoginMapper
import com.kh.sbilyhour.composestructure.data.remote.api.LoginApi
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import com.kh.sbilyhour.composestructure.domain.repository.LoginRepository
import com.kh.sbilyhour.composestructure.utils.ApiErrorHandler
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginApi,
    private val mapper: LoginMapper,
    private val apiErrorHandler: ApiErrorHandler
) : LoginRepository {
    override suspend fun login(request: LoginRequest): BaseResponse<LoginResponse> {
        return apiErrorHandler.handleApiCall(
            apiCall = {
                val requestDto = mapper.toLoginRequestDto(request)
                // 1. Map user credentials to LoginRequestDto
                val requestResult = mapper.mapLoginRequest(requestDto)
                // 2. Call API and get response
                val response = api.login(requestResult)
                // 3. Map LoginResponseDto to domain-level model (LoginResponse)
                val loginResult = mapper.toLoginResponseDto(response.data)
                val loginResponse = mapper.mapToLoginResponse(loginResult)
                if (response.statusCode == 200) {
                    saveUserLogin(loginResponse)
                }
                loginResponse
            }
        )
    }

    override suspend fun saveUserLogin(response: LoginResponse?): Boolean {
        return try {
            // Convert domain-level model (LoginResponse) to a DTO if needed
            val loginResponseDto = mapper.toLoginResponseDto(response)
            // Save login details locally (e.g., in SharedPreferences or a database)
            true
        } catch (e: Exception) {
            false
        }
    }
}
