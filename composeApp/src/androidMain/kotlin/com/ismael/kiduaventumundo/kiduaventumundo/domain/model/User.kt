package com.ismael.kiduaventumundo.kiduaventumundo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(

    val id: Long = 0L,

    val name: String,

    val age: Int,

    val nickname: String,

    val passwordHash: String,

    val avatarId: String,

    val securityQuestion: String,

    val securityAnswerHash: String,

    val stars: Int = 0
)