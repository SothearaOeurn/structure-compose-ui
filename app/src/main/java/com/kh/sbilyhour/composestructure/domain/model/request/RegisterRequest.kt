package com.kh.sbilyhour.composestructure.domain.model.request

data class RegisterRequest(
    val username: String,
    val email:String,
    val password: String
)