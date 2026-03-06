package com.ismael.kiduaventumundo.kiduaventumundo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismael.kiduaventumundo.datasource.repository.UserRepositoryImpl

import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.ismael.kiduaventumundo.kiduaventumundo.datasource.session.SessionManager

class LoginViewModel(
    private val repository: UserRepositoryImpl = UserRepositoryImpl()
) : ViewModel() {

    // =============================
    // STATES
    // =============================

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var loginSuccess by mutableStateOf(false)
        private set


    // =============================
    // LOGIN
    // =============================
    fun login(
        nickname: String,
        password: String
    ) {

        if (nickname.isBlank() || password.isBlank()) {
            errorMessage = "Completa todos los campos"
            return
        }

        viewModelScope.launch {

            isLoading = true
            errorMessage = null

            val success = repository.login(
                nickname,
                password
            )

            if (success) {

                // Guardar sesión
                SessionManager.saveToken(nickname)

                loginSuccess = true

            } else {

                errorMessage = "Usuario o contraseña incorrectos"

            }

            isLoading = false
        }
    }
}