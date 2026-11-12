package com.example.bibliotecapessoal.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bibliotecapessoal.screens.CadastroScreen
import com.example.bibliotecapessoal.screens.DetalhesScreen
import com.example.bibliotecapessoal.screens.HomeScreen
import com.example.bibliotecapessoal.screens.SobreScreen
import com.example.bibliotecapessoal.viewmodel.LivroViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Criado acima dos destinos: todas as telas usam a MESMA coleção de livros.
    val viewModel: LivroViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rotas.HOME) {

        composable(Rotas.HOME) {
            HomeScreen(
                livros = viewModel.livros,
                onAbrirDetalhes = { id -> navController.navigate(Rotas.detalhes(id)) },
                onNovoLivro = { navController.navigate(Rotas.CADASTRO) { launchSingleTop = true } },
                onSobre = { navController.navigate(Rotas.SOBRE) { launchSingleTop = true } }
            )
        }

        composable(Rotas.CADASTRO) {
            CadastroScreen(
                onSalvar = { titulo, autor, paginas, genero, resenha ->
                    viewModel.adicionar(titulo, autor, paginas, genero, resenha)
                    navController.popBackStack() // volta à lista, sem empilhar outra Home
                },
                onCancelar = { navController.popBackStack() }
            )
        }

        composable(
            route = Rotas.DETALHES,
            arguments = listOf(navArgument(Rotas.ARG_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(Rotas.ARG_ID) ?: -1
            DetalhesScreen(
                livro = viewModel.buscarPorId(id), // null => a tela mostra "não encontrado"
                onVoltar = { navController.popBackStack() }
            )
        }

        composable(Rotas.SOBRE) {
            SobreScreen(onVoltar = { navController.popBackStack() })
        }
    }
}
