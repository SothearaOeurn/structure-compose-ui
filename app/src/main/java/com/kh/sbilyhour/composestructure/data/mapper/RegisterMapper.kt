package com.kh.sbilyhour.composestructure.data.mapper

import com.kh.sbilyhour.composestructure.data.model.request.RegisterRequestDto
import com.kh.sbilyhour.composestructure.data.model.response.LoginResponseDto
import com.kh.sbilyhour.composestructure.domain.model.request.RegisterRequest
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

class RegisterMapper {

    fun mapRegisterRequest(requestDto: RegisterRequestDto): RegisterRequest {
        return RegisterRequest(
            username = requestDto.username,
            email = requestDto.email,
            password = requestDto.password
        )
    }

    // Convert from LoginRequestDto (API Request) to LoginEntity (Local Database entity)
    fun toRegisterRequestDto(request: RegisterRequest): RegisterRequestDto {
        return RegisterRequestDto(
            username = request.username,
            email = request.email,
            password = request.password
        )
    }

    // Convert from LoginResponseDto (API Response) to LoginEntity (Local Database entity)
    fun mapToRegisterResponse(dto: LoginResponseDto?): LoginResponse {
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
    fun toRegisterResponseDto(entity: LoginResponse?): LoginResponseDto {
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
