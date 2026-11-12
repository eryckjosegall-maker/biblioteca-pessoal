package com.example.bibliotecapessoal

import com.example.bibliotecapessoal.viewmodel.LivroViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class LivroViewModelTest {

    @Test
    fun comecaComTresLivros() {
        assertEquals(3, LivroViewModel().livros.size)
    }

    @Test
    fun adicionarCriaIdUnicoEPodeSerBuscado() {
        val vm = LivroViewModel()
        val novo = vm.adicionar("Livro Teste", "Autora", 100, "", "")
        assertEquals(4, vm.livros.size)
        assertEquals(novo, vm.buscarPorId(novo.id))
        assertEquals(vm.livros.map { it.id }.toSet().size, vm.livros.size)
    }

    @Test
    fun idInexistenteRetornaNull() {
        assertNull(LivroViewModel().buscarPorId(999))
        assertNotNull(LivroViewModel().buscarPorId(1))
    }
}
