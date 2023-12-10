package com.rmaprojects.shared.core.domain.usecase

import com.rmaprojects.shared.core.domain.usecase.auth.AuthUseCase

interface ErbeChatUseCase {
    val authUseCase: AuthUseCase
}