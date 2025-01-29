package com.kh.sbilyhour.composestructure.data.mapper

import com.kh.sbilyhour.composestructure.data.remote.model.request.LoginRequestDto
import com.kh.sbilyhour.composestructure.data.remote.model.response.LoginResponseDto
import com.kh.sbilyhour.composestructure.domain.model.request.LoginRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

class LoginMapper {

    fun mapLoginRequest(requestDto: LoginRequestDto): LoginRequest {
        return LoginRequest(username = requestDto.username, password = requestDto.password)
    }
    // Convert from LoginRequestDto (API Request) to LoginEntity (Local Database entity)
    fun toLoginRequestDto(request: LoginRequest): LoginRequestDto {
        return LoginRequestDto(
            username = request.username,
            password = request.password
        )
    }

    // Convert from LoginResponseDto (API Response) to LoginEntity (Local Database entity)
    fun mapToLoginResponse(dto: LoginResponseDto?): LoginResponse {
        return LoginResponse(
            accessToken = dto?.accessToken ?: "",
            expiresIn = dto?.expiresIn ?: 0.0,
            refreshExpiresIn = dto?.refreshExpiresIn ?: 0.0,
            refreshToken = dto?.refreshToken ?: "",
            tokenType = dto?.tokenType ?: "",
            notBeforePolicy = dto?.notBeforePolicy ?: "",
            sessionState = dto?.sessionState ?: "",
            scope = dto?.scope ?: ""
        )
    }

    // Convert from LoginEntity (Local Database entity) to LoginResponseDto (API Response)
    fun toLoginResponseDto(entity: LoginResponse?): LoginResponseDto {
        return LoginResponseDto(
            accessToken = entity?.accessToken ?: "",
            expiresIn = entity?.expiresIn ?: 0.0,
            refreshExpiresIn = entity?.refreshExpiresIn ?: 0.0,
            refreshToken = entity?.refreshToken ?: "",
            tokenType = entity?.tokenType ?: "",
            notBeforePolicy = entity?.notBeforePolicy ?: "",
            sessionState = entity?.sessionState ?: "",
            scope = entity?.scope ?: ""
        )
    }
}
