package com.rmaprojects.shared.core.presentation.ui.screen.splash

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.rmaprojects.shared.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: AuthRepository
): ScreenModel {


    fun checkLogin(isLoggedIn: (Boolean) -> Unit) {
        screenModelScope.launch {
            try {
                val currentUser = repository.retrieveCurrentSettings()
                isLoggedIn(currentUser.name.isNotEmpty())
            } catch (e: Exception) {
                println(e.toString())
                isLoggedIn(false)
            }
        }
    }

}