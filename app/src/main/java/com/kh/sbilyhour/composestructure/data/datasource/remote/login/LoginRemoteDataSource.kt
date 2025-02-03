package com.kh.sbilyhour.composestructure.data.datasource.remote.login

import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

interface LoginRemoteDataSource {
    suspend fun login(request: LoginRequest): BaseResponse<LoginResponse>
}