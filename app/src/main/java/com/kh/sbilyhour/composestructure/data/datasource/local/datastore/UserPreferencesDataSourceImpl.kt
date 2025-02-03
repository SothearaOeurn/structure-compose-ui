package com.kh.sbilyhour.composestructure.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import com.kh.sbilyhour.composestructure.domain.model.response.LoginResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferencesDataSourceImpl @Inject constructor(@ApplicationContext context: Context) :
    UserPreferencesDataSource {

    private val dataStore: DataStore<User.UserProto> = context.dataStore

    val userPreferencesFlow: Flow<User.UserProto> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(User.UserProto.getDefaultInstance())
            } else {
                throw exception
            }
        }

    // Save user data to DataStore
    override suspend fun saveUser(response: LoginResponse?) {
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .setAccessToken(response?.accessToken ?: "")
                .setExpiresIn(response?.expiresIn ?: 0.0)
                .setRefreshToken(response?.refreshToken ?: "")
                .setScope(response?.scope ?: "")
                .build()
        }
    }

    // Get the current user stored in DataStore
    override suspend fun getUser(): User.UserProto {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(User.UserProto.getDefaultInstance())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences
            }
            .first() // Block until data is received (you can also return a Flow if you want to observe changes)
    }

    // Clear the user data from DataStore
    override suspend fun clearUser() {
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .clear()
                .build()
        }
    }
}

// Extension to create a DataStore instance
private val Context.dataStore: DataStore<User.UserProto> by dataStore(
    fileName = "user_prefs.pb",
    serializer = UserPreferencesSerializer
)

