package com.kh.sbilyhour.composestructure.data.datasource.remote.register

import com.kh.sbilyhour.composestructure.data.api.AuthApi
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import jakarta.inject.Inject

class RegisterRemoteDataSourceImpl @Inject constructor(
    private val userApi: AuthApi
) : RegisterRemoteDataSource {

    override suspend fun register(request: RegisterRequest): BaseResponse<LoginResponse> {
        return userApi.register(request)
    }
}
