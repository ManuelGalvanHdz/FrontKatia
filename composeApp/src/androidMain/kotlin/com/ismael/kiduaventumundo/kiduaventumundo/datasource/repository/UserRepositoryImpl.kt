/*package com.ismael.kiduaventumundo.kiduaventumundo.datasource.repository

import com.ismael.kiduaventumundo.kiduaventumundo.back.db.AppDatabaseHelper
import com.ismael.kiduaventumundo.kiduaventumundo.com.ismael.kiduaventumundo.kiduaventumundo.domain.model.User
import com.ismael.kiduaventumundo.kiduaventumundo.domain.repository.userRepository

class UserRepositoryImpl(
    private val db: AppDatabaseHelper
) : userRepository{

    override fun register(user: User): Long {
        return db.registerUser(user)
    }

    override fun getUserById(id: Long): User? {
        return db.getUserById(id)
    }
    override fun getUserByNickname(nickname: String): User? {
        return db.getUserByNickname(nickname)
    }


    override fun nicknameExists(nickname: String): Boolean {
        return db.nicknameExists(nickname)
    }
    override fun updateUser(user: User) {
        db.updateUser(user)
    }


    override fun setSession(userId: Long) {
        db.setSession(userId)
    }

    override fun getSessionUserId(): Long? {
        return db.getSessionUserId()
    }

    override fun clearSession() {
        db.clearSession()
    }
}*/

package com.ismael.kiduaventumundo.datasource.repository

import com.ismael.kiduaventumundo.datasource.api.UserApi
import com.ismael.kiduaventumundo.kiduaventumundo.domain.model.User
import com.ismael.kiduaventumundo.kiduaventumundo.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: UserApi = UserApi()
) : UserRepository {

    // =============================
    // REGISTRAR USUARIO
    // =============================
    override suspend fun createUser(user: User) {
        api.createUser(user)
    }

    // =============================
    // OBTENER USUARIO POR NICKNAME
    // =============================
    override suspend fun getUserByNickname(nickname: String): User? {
        return api.getUserByNickname(nickname)
    }

    // =============================
    // LOGIN
    // =============================
    override suspend fun login(
        nickname: String,
        password: String
    ): Boolean {

        return api.login(nickname, password)

    }
}