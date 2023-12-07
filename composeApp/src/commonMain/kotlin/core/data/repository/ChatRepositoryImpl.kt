package core.data.repository

import core.data.source.remote.RemoteDataSource
import core.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,

): ChatRepository {
    override suspend fun signIn(username: String, password: String) {
        remoteDataSource.signInUser(username, password)
    }

    override suspend fun signUp(name: String, username: String, password: String) {
        remoteDataSource.signUpUser(username, name, password)
    }

}