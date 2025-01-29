package com.kh.sbilyhour.composestructure.domain.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    var accessToken:String?=null,

    @SerializedName("expires_in")
    var expiresIn:Double?=null,

    @SerializedName("refresh_expires_in")
    var refreshExpiresIn:Double?=null,

    @SerializedName("refresh_token")
    var refreshToken:String?=null,

    @SerializedName("token_type")
    var tokenType:String?=null,

    @SerializedName("not-before-policy")
    var notBeforePolicy:String?=null,

    @SerializedName("session_state")
    var sessionState:String?=null,

    @SerializedName("scope")
    var scope:String?=null
)