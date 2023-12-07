package presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun WindowIcon(
    icon: ImageVector,
    whatFor: String,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier.size(18.dp),
        imageVector = icon,
        contentDescription = whatFor,
    )
}