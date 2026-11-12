package com.example.bibliotecapessoal.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.bibliotecapessoal.model.Livro

/**
 * Coleção ÚNICA de livros, compartilhada por listagem, cadastro e detalhes.
 * Como é um mutableStateListOf, qualquer tela que lê a lista recompõe sozinha
 * quando um livro é adicionado (sem precisar reiniciar o app).
 */
class LivroViewModel : ViewModel() {

    private var proximoId = 4

    val livros = mutableStateListOf(
        Livro(
            id = 1,
            titulo = "Dom Casmurro",
            autor = "Machado de Assis",
            paginas = 256,
            genero = "Romance",
            resenha = "Bentinho narra sua história e a dúvida sobre Capitu. Um clássico da literatura brasileira."
        ),
        Livro(
            id = 2,
            titulo = "O Hobbit",
            autor = "J. R. R. Tolkien",
            paginas = 310,
            genero = "Fantasia",
            resenha = "A aventura de Bilbo Bolseiro ao lado de treze anões para recuperar a Montanha Solitária."
        ),
        Livro(
            id = 3,
            titulo = "Código Limpo",
            autor = "Robert C. Martin",
            paginas = 425,
            genero = "Tecnologia",
            resenha = "Boas práticas para escrever código legível e fácil de manter."
        )
    )

    /** Cria um livro com ID único e o acrescenta à coleção compartilhada. */
    fun adicionar(
        titulo: String,
        autor: String,
        paginas: Int,
        genero: String,
        resenha: String
    ): Livro {
        val novo = Livro(
            id = proximoId++,
            titulo = titulo.trim(),
            autor = autor.trim(),
            paginas = paginas,
            genero = genero.trim().ifBlank { "Não informado" },
            resenha = resenha.trim().ifBlank { "Sem resenha." }
        )
        livros.add(novo)
        return novo
    }

    /** Busca pelo ID recebido na rota. Retorna null se não existir (a tela trata esse caso). */
    fun buscarPorId(id: Int): Livro? = livros.firstOrNull { it.id == id }
}
