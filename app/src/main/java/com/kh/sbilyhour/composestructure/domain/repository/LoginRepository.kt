package com.kh.sbilyhour.composestructure.domain.repository


import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

interface LoginRepository {

    suspend fun login(request: LoginRequest): BaseResponse<LoginResponse>

    suspend fun saveUserLogin(response: LoginResponse?):Boolean
}