package com.learnhub.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val PurplePrimary = Color(0xFF6750A4)
val PurpleSecondary = Color(0xFF8572C5)
val PurpleContainer = Color(0xFFEADDFF)

val ScreenBackground = Color(0xFFFFFFFF)
val SurfaceWhite = Color(0xFFFFFFFF)

val TextPrimary = Color(0xFF24212E)
val TextSecondary = Color(0xFF777482)
val InputBorder = Color(0xFFD8D4E2)

val ErrorRed = Color(0xFFBA1A1A)

private val DarkColorScheme = darkColorScheme(
    primary = PurpleSecondary,
    secondary = PurpleContainer,
    tertiary = PurplePrimary,
    background = Color(0xFF121116),
    surface = Color(0xFF121116),
    onPrimary = Color.White,
    onSecondary = TextPrimary,
    onBackground = Color(0xFFF4F0FA),
    onSurface = Color(0xFFF4F0FA),
    outline = InputBorder,
    error = ErrorRed
)

private val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = Color.White,
    secondary = PurpleSecondary,
    onSecondary = Color.White,
    tertiary = PurplePrimary,
    background = ScreenBackground,
    onBackground = TextPrimary,
    surface = SurfaceWhite,
    onSurface = TextPrimary,
    surfaceVariant = PurpleContainer,
    onSurfaceVariant = TextSecondary,
    outline = InputBorder,
    error = ErrorRed
)

@Composable
fun LearnHubTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}