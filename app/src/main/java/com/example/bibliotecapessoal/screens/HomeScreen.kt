package com.example.bibliotecapessoal.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bibliotecapessoal.model.Livro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    livros: List<Livro>,
    onAbrirDetalhes: (Int) -> Unit,
    onNovoLivro: () -> Unit,
    onSobre: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Biblioteca Pessoal") },
                actions = { TextButton(onClick = onSobre) { Text("Sobre") } }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onNovoLivro) { Text("+ Novo livro") }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "${livros.size} livro(s) na sua estante",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(livros, key = { it.id }) { livro ->
                LivroCard(livro = livro, onAbrirDetalhes = { onAbrirDetalhes(livro.id) })
            }
        }
    }
}

@Composable
private fun LivroCard(livro: Livro, onAbrirDetalhes: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(livro.titulo, style = MaterialTheme.typography.titleLarge)
            Text(livro.autor, style = MaterialTheme.typography.bodyLarge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${livro.genero} • ${livro.paginas} páginas",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp)
                )
                TextButton(onClick = onAbrirDetalhes) { Text("Ver detalhes") }
            }
        }
    }
}
