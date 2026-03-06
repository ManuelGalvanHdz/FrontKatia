package com.ismael.kiduaventumundo.kiduaventumundo.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ismael.kiduaventumundo.kiduaventumundo.datasource.repository.SessionRepository

class SplashViewModel(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    var hasSession by mutableStateOf(false)
        private set

    init {
        checkSession()
    }

    private fun checkSession() {
        hasSession = sessionRepository.hasSession()
    }
}