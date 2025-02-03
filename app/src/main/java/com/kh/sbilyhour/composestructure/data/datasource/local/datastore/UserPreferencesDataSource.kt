package com.kh.sbilyhour.composestructure.data.datasource.local.datastore

import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse

interface UserPreferencesDataSource {

    suspend fun saveUser(response: LoginResponse?)

    suspend fun getUser(): User.UserProto

    suspend fun clearUser()
}