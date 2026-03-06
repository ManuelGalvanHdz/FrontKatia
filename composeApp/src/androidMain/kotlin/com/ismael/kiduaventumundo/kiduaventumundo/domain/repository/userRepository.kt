package com.ismael.kiduaventumundo.kiduaventumundo.domain.repository

import com.ismael.kiduaventumundo.kiduaventumundo.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User)

    suspend fun getUserByNickname(nickname: String): User?

    suspend fun login(
        nickname: String,
        password: String
    ): Boolean
}