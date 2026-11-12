package com.example.bibliotecapessoal.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
    onSalvar: (titulo: String, autor: String, paginas: Int, genero: String, resenha: String) -> Unit,
    onCancelar: () -> Unit
) {
    // Estado do formulário (sobrevive a rotação de tela)
    var titulo by rememberSaveable { mutableStateOf("") }
    var autor by rememberSaveable { mutableStateOf("") }
    var paginas by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf("") }
    var resenha by rememberSaveable { mutableStateOf("") }
    var tentouSalvar by rememberSaveable { mutableStateOf(false) }

    // Validações
    val erroTitulo = if (titulo.isBlank()) "O título é obrigatório" else null
    val erroAutor = if (autor.isBlank()) "Informe o autor" else null
    val numeroPaginas = paginas.trim().toIntOrNull()
    val erroPaginas = when {
        paginas.isBlank() -> "Informe o número de páginas"
        numeroPaginas == null -> "Digite apenas números"
        numeroPaginas <= 0 -> "O número de páginas deve ser maior que zero"
        else -> null
    }
    val valido = erroTitulo == null && erroAutor == null && erroPaginas == null

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Novo livro") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Campo(titulo, { titulo = it }, "Título *", erroTitulo, tentouSalvar)
            Campo(autor, { autor = it }, "Autor *", erroAutor, tentouSalvar)
            Campo(
                valor = paginas,
                aoMudar = { paginas = it },
                rotulo = "Número de páginas *",
                erro = erroPaginas,
                mostrarErro = tentouSalvar,
                teclado = KeyboardType.Number
            )
            Campo(genero, { genero = it }, "Gênero", null, false)
            Campo(resenha, { resenha = it }, "Resenha / anotações", null, false, linhasMin = 3)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(onClick = onCancelar, modifier = Modifier.weight(1f)) {
                    Text("Cancelar")
                }
                Button(
                    onClick = {
                        tentouSalvar = true
                        if (valido && numeroPaginas != null) {
                            onSalvar(titulo, autor, numeroPaginas, genero, resenha)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) { Text("Salvar") }
            }
        }
    }
}

@Composable
private fun Campo(
    valor: String,
    aoMudar: (String) -> Unit,
    rotulo: String,
    erro: String?,
    mostrarErro: Boolean,
    teclado: KeyboardType = KeyboardType.Text,
    linhasMin: Int = 1
) {
    OutlinedTextField(
        value = valor,
        onValueChange = aoMudar,
        label = { Text(rotulo) },
        isError = mostrarErro && erro != null,
        supportingText = { if (mostrarErro && erro != null) Text(erro) },
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        minLines = linhasMin,
        modifier = Modifier.fillMaxWidth()
    )
}
