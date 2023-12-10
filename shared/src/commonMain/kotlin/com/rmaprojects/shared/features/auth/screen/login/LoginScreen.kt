package com.rmaprojects.shared.features.auth.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.rmaprojects.shared.core.utils.UiEvent
import com.rmaprojects.shared.features.chats.screen.chat.ChatScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginScreen : Screen {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun Content() {
        val viewModel: LoginViewModel = getScreenModel()
        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.currentOrThrow
        val scope = rememberCoroutineScope()


        val isExpanded = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Medium
        val loginScreenState = viewModel.loginScreenState
        val snackbarHostState = SnackbarHostState()
        val eventFlow = viewModel.eventFlow.collectAsState()


        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                isExpanded
            ) {
                Row {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(48.dp),
                    )
                    LoginLeftSide(
                        modifier = Modifier
                            .width(500.dp)
                            .padding(24.dp)
                    )
                }
            }
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                }
            ) { innerPadding ->
                LoginContent(
                    modifier = if (isExpanded) Modifier.padding(innerPadding)
                        .padding(horizontal = 16.dp)
                    else Modifier.padding(innerPadding).padding(horizontal = 12.dp),
                    state = loginScreenState,
                    onUsernameUpdate = {
                        viewModel.onEvent(LoginScreenEvent.FillUsername(it))
                    },
                    onPasswordUpdate = {
                        viewModel.onEvent(LoginScreenEvent.FillPassword(it))
                    },
                    onLoginClick = {
                        viewModel.onEvent(LoginScreenEvent.Login)
                    },
                    onEmptyField = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Password and Username still empty")
                        }
                    },
                    isLoading = viewModel.loginScreenState.isLoading
                )
            }
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(108.dp),
                        painter = painterResource("erbechat_logo.svg"),
                        contentDescription = "ErbeChat Logo",
                    )
                    Spacer(modifier = Modifier.width(16.dp))
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
                }
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
        isLoading: Boolean,
        onUsernameUpdate: (String) -> Unit,
        onPasswordUpdate: (String) -> Unit,
        onEmptyField: () -> Unit,
        onLoginClick: () -> Unit,
    ) {

        var isShowPassword by rememberSaveable {
            mutableStateOf(true)
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.width(450.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login ke ErbeChat",
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
                    },
                    visualTransformation = if (isShowPassword) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                isShowPassword = !isShowPassword
                            }
                        ) {
                            Icon(
                                if (!isShowPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    modifier = modifier.fillMaxWidth(),
                    enabled = !isLoading,
                    onClick = {
                        if (state.passwordField.isEmpty() || state.usernameField.isEmpty()) {
                            onEmptyField()
                            return@Button
                        }
                        onLoginClick()
                    },
                ) {
                    Text("Login")
                }
                AnimatedVisibility(isLoading) {
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}