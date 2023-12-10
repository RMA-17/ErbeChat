package com.rmaprojects.shared.core.domain.interactor

import com.rmaprojects.shared.core.domain.repository.AuthRepository
import com.rmaprojects.shared.core.domain.usecase.ErbeChatUseCase
import com.rmaprojects.shared.core.domain.usecase.auth.AuthUseCase

class UseCaseInteractor(
    private val authRepository: AuthRepository
): ErbeChatUseCase {
    override val authUseCase: AuthUseCase
        get() = AuthUseCase(authRepository)
}