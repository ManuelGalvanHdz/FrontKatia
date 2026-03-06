package com.ismael.kiduaventumundo.datasource.api

import com.ismael.kiduaventumundo.kiduaventumundo.domain.model.User
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class UserApi {

    // =============================
    // CREAR USUARIO (REGISTER)
    // =============================
    suspend fun createUser(user: User) {

        ApiClient.client.post("http://10.0.2.2:8080/users") {

            contentType(ContentType.Application.Json)

            setBody(user)
        }
    }


    // =============================
    // OBTENER USUARIO POR NICKNAME
    // =============================
    suspend fun getUserByNickname(nickname: String): User? {

        return try {

            ApiClient.client
                .get("http://10.0.2.2:8080/users/$nickname")
                .body()

        } catch (e: Exception) {

            null
        }
    }


    // =============================
    // LOGIN
    // =============================
    suspend fun login(
        nickname: String,
        password: String
    ): Boolean {

        return try {

            val response = ApiClient.client.post("http://10.0.2.2:8080/login") {

                contentType(ContentType.Application.Json)

                setBody(
                    mapOf(
                        "nickname" to nickname,
                        "password" to password
                    )
                )
            }

            response.status == HttpStatusCode.OK

        } catch (e: Exception) {

            false
        }

    }
}