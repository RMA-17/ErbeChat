package com.rmaprojects.shared.core.domain.usecase.auth

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.rmaprojects.shared.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow

class AuthUseCase(
    private val authRepository: AuthRepository
) {

    fun signIn(
        username: String,
        password: String
    ) = flow<Result<Boolean, Exception>> {
        try {
            authRepository.signIn(username, password)
            emit(Ok(true))
        } catch (e: Exception) {
            emit(Err(e))
        }
    }

}