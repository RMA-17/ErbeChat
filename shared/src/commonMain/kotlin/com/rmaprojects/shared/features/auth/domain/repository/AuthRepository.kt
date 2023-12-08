package com.rmaprojects.shared.features.auth.domain.repository

interface AuthRepository {
    suspend fun signIn(
        username: String,
        password: String
    )
    suspend fun signUp(
        name: String,
        username: String,
        password: String
    )
}