package com.kh.sbilyhour.composestructure.domain.repository

import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

interface RegisterRepository {
    suspend fun register(request: RegisterRequest): BaseResponse<LoginResponse>
}