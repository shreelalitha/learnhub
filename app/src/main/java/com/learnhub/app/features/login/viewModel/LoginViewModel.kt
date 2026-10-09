package com.learnhub.app.features.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun login(
        email: String,
        pwd: String
    ){
        if(email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()){
            _uiState.value = LoginUiState.Error("invalid_email")
            return
        }

        if(pwd.isBlank() || pwd.length<6){
            _uiState.value = LoginUiState.Error("invalid_password")
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            delay(800.milliseconds)
            _uiState.value = LoginUiState.Success
        }
    }

    fun resetState() {
        _uiState.value = LoginUiState.Idle
    }
}