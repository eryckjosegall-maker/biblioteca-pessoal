package com.example.bibliotecapessoal.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private const val TEXTO_COMPARTILHAR =
    "Estou usando o app Biblioteca Pessoal, feito em Kotlin com Jetpack Compose, " +
        "para organizar os livros que eu leio: cadastro, lista e detalhes de cada livro."

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreScreen(onVoltar: () -> Unit) {
    val context = LocalContext.current

    // Intent implícito: o Android abre o seletor de apps compatíveis com ACTION_SEND
    fun compartilhar() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, TEXTO_COMPARTILHAR)
        }
        try {
            context.startActivity(Intent.createChooser(intent, "Compartilhar aplicativo"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "Nenhum aplicativo disponível para compartilhar.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Sobre / Ajuda") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Biblioteca Pessoal", style = MaterialTheme.typography.headlineMedium)

            Secao(
                "Finalidade",
                "Organizar os livros que você leu ou quer ler, guardando título, autor, " +
                    "número de páginas, gênero e uma resenha."
            )
            Secao(
                "Como usar",
                "1. Na tela inicial, veja a lista de livros.\n" +
                    "2. Toque em \"Ver detalhes\" para abrir as informações de um livro.\n" +
                    "3. Toque em \"+ Novo livro\" para cadastrar; título, autor e páginas são obrigatórios.\n" +
                    "4. Use o botão Voltar ou Cancelar para retornar sem alterar nada."
            )
            Secao("Autor", "Eryck José Segall — IFES, Desenvolvimento Mobile")

            Button(onClick = { compartilhar() }) { Text("Compartilhar aplicativo") }
            OutlinedButton(onClick = onVoltar) { Text("Voltar") }
        }
    }
}

@Composable
private fun Secao(titulo: String, texto: String) {
    Column {
        Text(titulo, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Text(texto, style = MaterialTheme.typography.bodyLarge)
    }
}
