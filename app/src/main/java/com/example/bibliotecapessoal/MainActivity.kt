package com.example.bibliotecapessoal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bibliotecapessoal.navigation.AppNavigation
import com.example.bibliotecapessoal.ui.theme.BibliotecaTheme

/** Única Activity do app: só inicia a interface Compose; a navegação fica em AppNavigation. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BibliotecaTheme {
                AppNavigation()
            }
        }
    }
}
