package core.domain.repository

interface ChatRepository {
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