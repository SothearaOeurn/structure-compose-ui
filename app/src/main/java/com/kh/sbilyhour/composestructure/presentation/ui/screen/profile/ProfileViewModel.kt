package com.kh.sbilyhour.composestructure.presentation.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import com.kh.sbilyhour.composestructure.presentation.ui.screen.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPreferences: UserPreferencesDataSource
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private val _user = MutableStateFlow(User("John Doe", "johndoe@example.com"))
    val user: StateFlow<User> = _user

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearUser()
        }
    }

    fun resetErrorState() {
        viewModelScope.launch { _homeState.emit(HomeState.Idle) }
    }
}

data class User(val name: String, val email: String)


