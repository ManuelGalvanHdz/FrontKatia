package com.ismael.kiduaventumundo.kiduaventumundo.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismael.kiduaventumundo.datasource.repository.UserRepositoryImpl
import com.ismael.kiduaventumundo.kiduaventumundo.domain.model.User
import kotlinx.coroutines.launch
class RegisterViewModel : ViewModel() {

    private val repository = UserRepositoryImpl()

    var uiState by mutableStateOf(RegisterUiState())
        private set


    fun registerUser() {

        viewModelScope.launch {

            if (
                uiState.name.isBlank() ||
                uiState.nickname.isBlank() ||
                uiState.password.isBlank()
            ) {

                uiState = uiState.copy(
                    error = "Completa todos los campos"
                )

                return@launch
            }

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val user = User(
                    name = uiState.name,
                    age = uiState.age,
                    nickname = uiState.nickname,
                    passwordHash = uiState.password,
                    avatarId = uiState.avatarId,
                    securityQuestion = uiState.securityQuestion,
                    securityAnswerHash = uiState.securityAnswer
                )

                repository.createUser(user)

                uiState = uiState.copy(
                    isLoading = false,
                    success = true
                )

            } catch (e: Exception) {

                uiState = uiState.copy(
                    isLoading = false,
                    error = "Error registrando usuario"
                )
            }
        }
    }

    fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun updateAge(age: Int) {
        uiState = uiState.copy(age = age)
    }

    fun updateNickname(nickname: String) {
        uiState = uiState.copy(nickname = nickname)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun updateSecurityQuestion(question: String) {
        uiState = uiState.copy(securityQuestion = question)
    }

    fun updateSecurityAnswer(answer: String) {
        uiState = uiState.copy(securityAnswer = answer)
    }

    fun updateAvatar(avatarId: String) {
        uiState = uiState.copy(avatarId = avatarId)
    }
}