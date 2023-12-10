package com.rmaprojects.shared.features.auth.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

class LoginScreen : Screen {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun Content() {
        val viewModel: LoginViewModel = getScreenModel()
        val windowSizeClass = calculateWindowSizeClass()
        val isExpanded = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Medium

        val loginScreenState = viewModel.loginScreenState

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                isExpanded
            ) {
                LoginLeftSide(
                    modifier = Modifier
                        .width(500.dp)
                        .padding(24.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(36.dp),
            )
            LoginContent(
                modifier = Modifier.padding(horizontal = 16.dp),
                state = loginScreenState.value,
                onUsernameUpdate = {
                    viewModel.onEvent(LoginScreenEvent.FillUsername(it))
                },
                onPasswordUpdate = {
                    viewModel.onEvent(LoginScreenEvent.FillPassword(it))
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(36.dp),
            )
        }
    }

    @Composable
    fun LoginLeftSide(
        modifier: Modifier = Modifier
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    buildAnnotatedString {
                        append("Welcome to ")
                        withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("ErbeChat")
                        }
                        append("")
                    },
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Silahkan login menggunakan akun MikroTik Kalian",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

    @Composable
    fun LoginContent(
        modifier: Modifier = Modifier,
        state: LoginScreenState,
        onUsernameUpdate: (String) -> Unit,
        onPasswordUpdate: (String) -> Unit,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Login ke ErbeChat",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = state.usernameField,
                onValueChange = onUsernameUpdate,
                singleLine = true,
                label = {
                    Text("Username")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = state.passwordField,
                onValueChange = onPasswordUpdate,
                singleLine = true,
                label = {
                    Text("Password")
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Text("Login")
            }
        }
    }

}