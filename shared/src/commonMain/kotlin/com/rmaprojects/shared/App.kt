package com.rmaprojects.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScaleTransition

@Composable
fun App(
    modifier: Modifier = Modifier
) {
    Navigator(
        screen = Splash()
    ) { navigator ->
        ScaleTransition(
            modifier = modifier,
            navigator = navigator
        )
    }
}

class Splash : Screen {

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            
        }
    }
}