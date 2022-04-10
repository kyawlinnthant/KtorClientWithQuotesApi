package klt.mdy.kotlinserializationtest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = BlueGray700,
    secondary = LightGreen200,
)

private val LightColorPalette = lightColors(
    primary = Amber500,
    secondary = LightGreen900,
)

@Composable
fun KotlinSerializationTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = BlueGray700
        )
        systemUiController.setStatusBarColor(
            color = BlueGray700
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Amber500
        )
        systemUiController.setStatusBarColor(
            color = Amber500
        )
    }
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}