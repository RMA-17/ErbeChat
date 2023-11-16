package presentation.windowcomponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.WindowScope

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WindowScope.WindowTopBar(
    onDoubleClick: () -> Unit,
    onMinimizeHover: (Boolean) -> Unit,
    onMinimizeClick: () -> Unit,
    onMaximizeHover: (Boolean) -> Unit,
    onMaximizeClick: () -> Unit,
    onExitHover: (Boolean) -> Unit,
    onExitClick: () -> Unit,
    appName: String,
    appNameTextStyle: TextStyle,
    appNameFontWeight: FontWeight,
    modifier: Modifier = Modifier
) = WindowDraggableArea(
    modifier = modifier.combinedClickable(
        onClick = {},
        onDoubleClick = onDoubleClick,
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    )
) {
    TopAppBar(
        title = {
            Text(
                text = appName,
                style = appNameTextStyle,
                fontWeight = appNameFontWeight
            )
        },
        actions = {
            WindowIconButton(
                onClick = onMinimizeClick,
                isHovered = onMinimizeHover,
                rippleColor = Color.Green
            ) {
                WindowIcon(
                    Icons.Default.KeyboardArrowDown,
                    "Minimize Screen"
                )
            }
            WindowIconButton(
                onClick = onMaximizeClick,
                isHovered = onMaximizeHover,
                rippleColor = Color.Yellow
            ) {
                WindowIcon(
                    Icons.Default.Fullscreen,
                    "Go Fullscreen"
                )
            }
            WindowIconButton(
                onClick = onExitClick,
                isHovered = onExitHover,
                rippleColor = Color.Red
            ) {
                WindowIcon(
                    Icons.Default.Close,
                    "Close the app"
                )
            }
        }
    )
}
