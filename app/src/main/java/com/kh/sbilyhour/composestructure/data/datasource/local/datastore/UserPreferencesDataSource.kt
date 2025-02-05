package com.kh.sbilyhour.composestructure.data.datasource.local.datastore

import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataSource {
    val isUserLoggedInFlow: Flow<Boolean>

    suspend fun saveUser(response: LoginResponse?)

    suspend fun getUser(): User.UserProto

    suspend fun clearUser()
}