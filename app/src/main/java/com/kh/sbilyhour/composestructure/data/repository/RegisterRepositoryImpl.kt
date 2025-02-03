package com.kh.sbilyhour.composestructure.data.repository

import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import com.kh.sbilyhour.composestructure.data.datasource.remote.register.RegisterRemoteDataSource
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import com.kh.sbilyhour.composestructure.data.error.ApiErrorHandler
import com.kh.sbilyhour.composestructure.data.mapper.RegisterMapper
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val mapper: RegisterMapper,
    private val apiErrorHandler: ApiErrorHandler,
    private val remoteDataSource: RegisterRemoteDataSource,
    private val userPreferences: UserPreferencesDataSource
) : RegisterRepository {
    override suspend fun register(request: RegisterRequest): BaseResponse<LoginResponse> {
        return apiErrorHandler.handleApiCall(
            apiCall = {
                val requestDto = mapper.toRegisterRequestDto(request)
                // 1. Map user credentials to LoginRequestDto
                val requestResult = mapper.mapRegisterRequest(requestDto)
                // 2. Call API and get response
                val response = remoteDataSource.register(requestResult)
                // 3. Map LoginResponseDto to domain-level model (LoginResponse)
                val loginResult = mapper.toRegisterResponseDto(response.data)
                val loginResponse = mapper.mapToRegisterResponse(loginResult)
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
