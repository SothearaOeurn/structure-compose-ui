package com.kh.sbilyhour.composestructure.data.datasource.remote.login

import com.kh.sbilyhour.composestructure.data.api.AuthApi
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import jakarta.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val userApi: AuthApi
) : LoginRemoteDataSource {

    override suspend fun login(request: LoginRequest): BaseResponse<LoginResponse> {
        return userApi.login(request)
    }
}
