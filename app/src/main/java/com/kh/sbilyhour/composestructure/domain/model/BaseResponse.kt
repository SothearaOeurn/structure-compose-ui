package com.kh.sbilyhour.composestructure.domain.model

data class BaseResponse<T>(
    val statusCode: Int,
    val message: String,
    val data: T? // Can be null if no data is available
)