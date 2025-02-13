package com.kh.sbilyhour.composestructure.data.repository

import com.kh.sbilyhour.composestructure.data.mapper.LoginMapper
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import com.kh.sbilyhour.composestructure.data.datasource.remote.login.LoginRemoteDataSource
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import com.kh.sbilyhour.composestructure.domain.repository.LoginRepository
import com.kh.sbilyhour.composestructure.data.error.ApiErrorHandler
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val mapper: LoginMapper,
    private val apiErrorHandler: ApiErrorHandler,
    private val remoteDataSource: LoginRemoteDataSource,
    private val userPreferences: UserPreferencesDataSource
) : LoginRepository {
    override suspend fun login(request: LoginRequest): BaseResponse<LoginResponse> {
        return apiErrorHandler.handleApiCall(
            apiCall = {
                val requestDto = mapper.toLoginRequestDto(request)
                // 1. Map user credentials to LoginRequestDto
                val requestResult = mapper.mapLoginRequest(requestDto)
                // 2. Call API and get response
                val response = remoteDataSource.login(requestResult)
                // 3. Map LoginResponseDto to domain-level model (LoginResponse)
                val loginResult = mapper.toLoginResponseDto(response.data)
                val loginResponse = mapper.mapToLoginResponse(loginResult)
                if (response.statusCode == 200) {
                    //4. Save data in data store
                    response.data?.let {
                        userPreferences.saveUser(it)
                    }
                }
                loginResponse
            }
        )
    }
}
