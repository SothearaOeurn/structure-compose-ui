package com.kh.sbilyhour.composestructure.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
) :
    ViewModel() {

    private val _loginState = MutableStateFlow<HomeState>(HomeState.Idle)
    val loginState: StateFlow<HomeState> = _loginState.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    init {
        viewModelScope.launch {
            val user = userPreferencesDataSource.getUser()
            _username.emit(user.accessToken)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferencesDataSource.clearUser()
            _username.emit("")
        }
    }

    fun resetErrorState() {
        viewModelScope.launch { _loginState.emit(HomeState.Idle) }
    }
}