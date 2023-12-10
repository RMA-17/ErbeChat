package com.rmaprojects.shared.core.data.repository

import com.rmaprojects.shared.core.data.source.local.LocalDataSource
import com.rmaprojects.shared.core.data.source.remote.RemoteDataSource
import com.rmaprojects.shared.core.domain.model.LocalUser
import com.rmaprojects.shared.core.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AuthRepository {
    override suspend fun signIn(username: String, password: String) {
        val result = remoteDataSource.signInUser(username, password)
        localDataSource.saveUserData(result)
    }

    override suspend fun signUp(name: String, username: String, password: String) {
        val result = remoteDataSource.signUpUser(username, name, password)
        localDataSource.saveUserData(result)
    }

    override suspend fun retrieveCurrentSettings(): LocalUser {
        return localDataSource.getUserData()
    }

    override suspend fun logOut() {
        remoteDataSource.signOutUser()
        localDataSource.deleteUserData()
    }
}