package com.midoriai.leaflogic.ui.theme

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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = LeafGreen80,
    onPrimary = NeutralGray20,
    primaryContainer = LeafGreen40,
    onPrimaryContainer = NeutralGray95,
    secondary = AccentOrange80,
    onSecondary = NeutralGray20,
    secondaryContainer = AccentOrange40,
    onSecondaryContainer = NeutralGray95,
    tertiary = NutritionalBlue80,
    onTertiary = NeutralGray20,
    tertiaryContainer = NutritionalBlue40,
    onTertiaryContainer = NeutralGray95,
    error = HealthRed80,
    onError = NeutralGray20,
    errorContainer = HealthRed40,
    onErrorContainer = NeutralGray95,
    background = NeutralGray10,
    onBackground = NeutralGray90,
    surface = NeutralGray10,
    onSurface = NeutralGray90,
    surfaceVariant = NeutralGray20,
    onSurfaceVariant = NeutralGray90,
    outline = NeutralGray90
)

private val LightColorScheme = lightColorScheme(
    primary = LeafGreen40,
    onPrimary = NeutralGray99,
    primaryContainer = LeafGreen90,
    onPrimaryContainer = NeutralGray10,
    secondary = AccentOrange40,
    onSecondary = NeutralGray99,
    secondaryContainer = AccentOrange90,
    onSecondaryContainer = NeutralGray10,
    tertiary = NutritionalBlue40,
    onTertiary = NeutralGray99,
    tertiaryContainer = NutritionalBlue90,
    onTertiaryContainer = NeutralGray10,
    error = HealthRed40,
    onError = NeutralGray99,
    errorContainer = HealthRed90,
    onErrorContainer = NeutralGray10,
    background = NeutralGray99,
    onBackground = NeutralGray10,
    surface = NeutralGray99,
    onSurface = NeutralGray10,
    surfaceVariant = NeutralGray95,
    onSurfaceVariant = NeutralGray10,
    outline = NeutralGray20
)

@Composable
fun LeafLogicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}