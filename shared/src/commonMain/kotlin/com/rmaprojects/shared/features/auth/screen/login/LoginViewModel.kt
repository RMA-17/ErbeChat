package com.rmaprojects.shared.features.auth.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.rmaprojects.shared.core.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ScreenModel {

    private val _loginScreenState = mutableStateOf(LoginScreenState())
    val loginScreenState = _loginScreenState

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.FillPassword -> {
                _loginScreenState.value = loginScreenState.value.copy(
                    passwordField = event.newValue
                )
            }

            is LoginScreenEvent.FillUsername -> {
                _loginScreenState.value = loginScreenState.value.copy(
                    usernameField = event.newValue
                )
            }

            is LoginScreenEvent.Login -> {
                screenModelScope.launch {
                    authRepository.signIn(
                        _loginScreenState.value.usernameField,
                        _loginScreenState.value.passwordField
                    )
                }
            }
        }
    }

}

data class LoginScreenState(
    val usernameField: String = "",
    val passwordField: String = ""
)

sealed class LoginScreenEvent {
    data class FillUsername(val newValue: String) : LoginScreenEvent()
    data class FillPassword(val newValue: String) : LoginScreenEvent()
    data object Login : LoginScreenEvent()
}