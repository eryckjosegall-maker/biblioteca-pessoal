package com.example.bibliotecapessoal.model

/** Item gerenciado pelo app. O [id] é único e é o argumento passado para a rota de detalhes. */
data class Livro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val paginas: Int,
    val genero: String,
    val resenha: String
)
