package presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isHovered: (Boolean) -> Unit,
    rippleColor: Color,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalRippleTheme provides WindowRippleTheme(rippleColor)
    ) {
        IconButton(
            modifier = modifier
                .onPointerEvent(PointerEventType.Enter) {
                    isHovered(true)
                }
                .onPointerEvent(PointerEventType.Exit) {
                    isHovered(false)
                },
            onClick = onClick,
        ) {
            content()
        }
    }
}

private class WindowRippleTheme(
    private val color: Color
) : RippleTheme {
    @Composable
    override fun defaultColor(): Color = color

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Black,
        lightTheme = !isSystemInDarkTheme()
    )
}