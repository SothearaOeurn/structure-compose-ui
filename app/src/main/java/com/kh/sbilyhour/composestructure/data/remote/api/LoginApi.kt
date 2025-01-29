package com.kh.sbilyhour.composestructure.data.remote.api

import com.kh.sbilyhour.composestructure.data.remote.model.request.LoginRequestDto
import com.kh.sbilyhour.composestructure.data.remote.model.response.LoginResponseDto
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest):BaseResponse<LoginResponse>
}