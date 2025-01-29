package com.kh.sbilyhour.composestructure.data.remote.model.response

data class LoginResponseDto(
    var accessToken: String? = null,
    var expiresIn: Double? = null,
    var refreshExpiresIn: Double? = null,
    var refreshToken: String? = null,
    var tokenType: String? = null,
    var notBeforePolicy: String? = null,
    var sessionState: String? = null,
    var scope: String? = null
)