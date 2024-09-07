package nl.ahmed.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    surface = EerieBlack,
    surfaceVariant = Night,
    onSurface = WhiteSmoke,
    primary = PersianGreen
)

private val LightColorScheme = lightColorScheme(
    surface = WhiteSmoke,
    surfaceVariant = FloralWhite,
    onSurface = EerieBlack,
    primary = PersianGreen
)

@Composable
fun BooksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
