package com.app.newsapp.ui.theme


import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Color(0xFF003258),
    primaryContainer = PrimaryContainer80,
    onPrimaryContainer = Primary80,
    secondary = Secondary80,
    onSecondary = Color(0xFF121212),
    secondaryContainer = SecondaryContainer80,
    onSecondaryContainer = Secondary80,
    tertiary = AccentTeal,
    onTertiary = Color(0xFF003135),
    background = Background80,
    onBackground = Color(0xFFE6E1E5),
    surface = Surface80,
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = SurfaceVariant80,
    onSurfaceVariant = Color(0xFFC4C7C5),
    error = Error80
)

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = PrimaryContainer40,
    onPrimaryContainer = Primary40,
    secondary = Secondary40,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = SecondaryContainer40,
    onSecondaryContainer = Secondary40,
    tertiary = AccentTeal,
    onTertiary = Color(0xFFFFFFFF),
    background = Background40,
    onBackground = Color(0xFF1C1B1F),
    surface = Surface40,
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = SurfaceVariant40,
    onSurfaceVariant = Color(0xFF49454F),
    error = Error40
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}