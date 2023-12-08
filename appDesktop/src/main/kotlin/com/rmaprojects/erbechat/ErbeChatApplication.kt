package com.rmaprojects.erbechat

import com.rmaprojects.shared.App
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.rmaprojects.shared.constants.APP_NAME
import com.rmaprojects.shared.di.initKoin
import kotlinx.coroutines.launch
import org.jetbrains.skiko.OS
import org.jetbrains.skiko.hostOs
import org.koin.core.Koin
import presentation.components.WindowTopBar
import presentation.ui.theme.ErbeChatTheme
import java.awt.Dimension

lateinit var koin: Koin

fun main() {

    koin = initKoin().koin

    return application {

        val state = rememberWindowState(
            placement = WindowPlacement.Floating,
            isMinimized = false,
        )

        Window(
            title = APP_NAME,
            onCloseRequest = ::exitApplication,
            transparent = true,
            undecorated = true,
            state = state
        ) {

            window.minimumSize = Dimension(374, 665)

            val snackbarHostState = SnackbarHostState()
            val scope = rememberCoroutineScope()
            var windowState by remember {
                mutableStateOf(WindowRootState())
            }

            ErbeChatTheme {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Scaffold(
                        topBar = {
                            WindowTopBar(
                                onDoubleClick = {
                                    if (state.placement == WindowPlacement.Floating) {
                                        state.placement = WindowPlacement.Maximized
                                    } else {
                                        state.placement = WindowPlacement.Floating
                                    }
                                },
                                onMinimizeClick = { state.isMinimized = !state.isMinimized },
                                onMaximizeClick = {
                                    if (state.placement == WindowPlacement.Floating) {
                                        if (hostOs == OS.MacOS) state.placement =
                                            WindowPlacement.Fullscreen
                                        else state.placement = WindowPlacement.Maximized
                                    } else {
                                        state.placement = WindowPlacement.Floating
                                    }
                                },
                                onExitClick = {
                                    exitApplication()
                                },
                                onExitHover = {
                                    windowState = windowState.copy(isExitHovered = it)
                                },
                                onMaximizeHover = {
                                    windowState = windowState.copy(isMaximizedHovered = it)
                                },
                                onMinimizeHover = {
                                    windowState = windowState.copy(isMinimizeHovered = it)
                                },
                                appName = APP_NAME,
                                appNameTextStyle = MaterialTheme.typography.titleMedium,
                                appNameFontWeight = FontWeight.Medium
                            )
                        },
                        snackbarHost = {
                            SnackbarHost(
                                snackbarHostState
                            )
                        }
                    ) { innerPadding ->
                        App(
                            modifier = Modifier.padding(innerPadding),
                            onCardClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = it
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

private data class WindowRootState(
    val isExitHovered: Boolean = false,
    val isMinimizeHovered: Boolean = false,
    val isMaximizedHovered: Boolean = false
)