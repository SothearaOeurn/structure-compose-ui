package com.kh.sbilyhour.composestructure.data.api

import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest):BaseResponse<LoginResponse>

    @POST("api/users/register")
    suspend fun register(@Body request: RegisterRequest):BaseResponse<LoginResponse>
}