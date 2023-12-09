package com.rmaprojects.shared.features.auth.presentation.ui.screen.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: LoginViewModel = getScreenModel()

        LoginScreenContent(
            viewModel = viewModel
        )
    }

}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Login Screen")

    }
}