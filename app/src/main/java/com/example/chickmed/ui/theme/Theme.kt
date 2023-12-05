package com.example.chickmed.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat
import com.example.chickmed.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ChickMedTheme(
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

    val myCustomFont = FontFamily(
        Font(R.font.poppins_regular),
        Font(R.font.poppins_italic, style = FontStyle.Italic),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_mediumitalic, FontWeight.Medium, style = FontStyle.Italic),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_bolditalic, FontWeight.Bold, style = FontStyle.Italic)
    )


    val defaultTypography = Typography()
    val typography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = myCustomFont),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = myCustomFont),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = myCustomFont),

        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = myCustomFont),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = myCustomFont),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = myCustomFont),

        titleLarge = defaultTypography.titleLarge.copy(fontFamily = myCustomFont),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = myCustomFont),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = myCustomFont),

        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = myCustomFont),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = myCustomFont),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = myCustomFont),

        labelLarge = defaultTypography.labelLarge.copy(fontFamily = myCustomFont),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = myCustomFont),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = myCustomFont)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}