package com.turkcell.ticketapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turkcell.domain.AuthRepository
import com.turkcell.domain.AuthSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            authRepository.login(email, password)
                .onSuccess { _uiState.value = LoginUiState.Success(it) }
                .onFailure { _uiState.value = LoginUiState.Error(it.message ?: "Hata oluştu") }
        }
    }
}

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val session: AuthSession) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}