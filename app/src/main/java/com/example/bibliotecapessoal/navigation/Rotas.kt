package com.example.bibliotecapessoal.navigation

/** Rotas dos quatro destinos do NavHost. */
object Rotas {
    const val ARG_ID = "livroId"

    const val HOME = "home"
    const val CADASTRO = "cadastro"
    const val DETALHES = "detalhes/{$ARG_ID}"
    const val SOBRE = "sobre"

    fun detalhes(id: Int) = "detalhes/$id"
}
