package com.ismael.kiduaventumundo.kiduaventumundo.datasource.repository

import com.ismael.kiduaventumundo.kiduaventumundo.datasource.session.SessionManager

class SessionRepository {

    fun hasSession(): Boolean {
        return SessionManager.hasSession()
    }
}