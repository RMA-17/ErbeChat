package com.rmaprojects.shared.features.auth.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.rmaprojects.shared.core.domain.usecase.ErbeChatUseCase
import com.rmaprojects.shared.core.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    useCases: ErbeChatUseCase
) : ScreenModel {

    private val authUseCase = useCases.authUseCase

    var loginScreenState by mutableStateOf(LoginScreenState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow().stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(500),
        initialValue = UiEvent.Idle
    )

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.FillPassword -> {
                loginScreenState = loginScreenState.copy(
                    passwordField = event.newValue
                )
            }

            is LoginScreenEvent.FillUsername -> {
                loginScreenState = loginScreenState.copy(
                    usernameField = event.newValue
                )
            }

            is LoginScreenEvent.Login -> {
                loginScreenState = loginScreenState.copy(
                    isLoading = true
                )
                screenModelScope.launch {
                    authUseCase.signIn(
                        loginScreenState.usernameField,
                        loginScreenState.passwordField
                    ).collect { result ->
                        println(result.toString())
                        when (result) {
                            is Err -> {
                                _eventFlow.emit(
                                    UiEvent.SnackbarMessage(
                                        result.error.message ?: "Error occurred"
                                    )
                                )
                            }
                            is Ok -> {
                                _eventFlow.emit(UiEvent.Success)
                            }
                        }
                    }
                }
                loginScreenState = loginScreenState.copy(
                    isLoading = false
                )
            }
        }
    }
}

data class LoginScreenState(
    val usernameField: String = "",
    val passwordField: String = "",
    val isLoading: Boolean = false
)

sealed class LoginScreenEvent {
    data class FillUsername(val newValue: String) : LoginScreenEvent()
    data class FillPassword(val newValue: String) : LoginScreenEvent()
    data object Login : LoginScreenEvent()
}