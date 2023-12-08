package com.rmaprojects.shared.features.auth.presentation.ui.screen.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val loginViewModel = getScreenModel<LoginViewModel>()

        LifecycleEffect(
            onStarted = {

            }
        )

        LoginScreenContent(
            viewModel = loginViewModel
        )
    }

}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {
    Scaffold(
        modifier = modifier
    ) {  innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }
}