package com.rmaprojects.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScaleTransition
import com.rmaprojects.shared.core.presentation.ui.screen.splash.SplashScreen

@Composable
fun App(
    modifier: Modifier = Modifier
) {

    Navigator(
        screen = SplashScreen()
    ) { navigator ->
        ScaleTransition(
            modifier = modifier,
            navigator = navigator
        )
    }

}