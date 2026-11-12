package com.example.bibliotecapessoal.screens

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bibliotecapessoal.model.Livro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesScreen(
    livro: Livro?,
    onVoltar: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalhes") },
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (livro == null) {
                // ID inexistente: mensagem amigável em vez de falha
                Text("Livro não encontrado", style = MaterialTheme.typography.headlineSmall)
                Text("Não existe nenhum livro com esse identificador.")
                Button(onClick = onVoltar) { Text("Voltar à lista") }
            } else {
                Text(livro.titulo, style = MaterialTheme.typography.headlineMedium)
                Text(livro.autor, style = MaterialTheme.typography.titleMedium)
                Info("Gênero", livro.genero)
                Info("Páginas", livro.paginas.toString())
                Info("Resenha", livro.resenha)
                Info("Código do livro", "#${livro.id}")
            }
        }
    }
}

@Composable
private fun Info(rotulo: String, valor: String) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        Text(rotulo, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Text(valor, style = MaterialTheme.typography.bodyLarge)
    }
}
