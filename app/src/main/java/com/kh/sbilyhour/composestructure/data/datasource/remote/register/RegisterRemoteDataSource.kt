package com.kh.sbilyhour.composestructure.data.datasource.remote.register

import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

interface RegisterRemoteDataSource {
    suspend fun register(request: RegisterRequest): BaseResponse<LoginResponse>
}