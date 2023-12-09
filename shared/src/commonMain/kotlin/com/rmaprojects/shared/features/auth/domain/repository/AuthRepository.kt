package com.rmaprojects.shared.features.auth.domain.repository

import com.rmaprojects.shared.features.auth.domain.model.LocalUser

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
    suspend fun retrieveCurrentSettings(): LocalUser
    suspend fun logOut()
}