package com.example.bibliotecapessoal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Claro = lightColorScheme(
    primary = Color(0xFF7A4B1E),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDCBE),
    onPrimaryContainer = Color(0xFF2C1600),
    background = Color(0xFFFFFBFF),
    surface = Color(0xFFFFFBFF)
)

private val Escuro = darkColorScheme(
    primary = Color(0xFFFFB878),
    onPrimary = Color(0xFF4A2800),
    primaryContainer = Color(0xFF6A3B08),
    onPrimaryContainer = Color(0xFFFFDCBE),
    background = Color(0xFF1F1B16),
    surface = Color(0xFF1F1B16)
)

@Composable
fun BibliotecaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) Escuro else Claro,
        content = content
    )
}
