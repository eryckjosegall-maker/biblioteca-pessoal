# Biblioteca Pessoal

Aplicativo Android para organizar os livros que a pessoa lê: mostra uma lista de livros, permite cadastrar novos livros e consultar os detalhes de cada um. Desenvolvido em Kotlin com Jetpack Compose, Material 3 e Navigation Compose, para a atividade avaliativa de Desenvolvimento Mobile (IFES).

## Autor
- Eryck José Segall

## Como executar
1. Abra a pasta do projeto no Android Studio e aguarde o Gradle sincronizar.
2. Escolha um emulador (ou dispositivo) e clique em **Run ▶**.
3. Não é preciso banco de dados, internet ou configuração extra.

## Requisitos implementados
- **RF01 — Listagem:** nome do app, 3 livros iniciais, cada um mostrando título, autor, gênero e páginas, com botão "Ver detalhes". Há botões para o cadastro ("+ Novo livro") e para a tela Sobre. A lista atualiza assim que um livro é salvo.
- **RF02 — Cadastro:** 5 campos (título*, autor*, páginas*, gênero, resenha). Título e autor são obrigatórios; páginas exige número inteiro maior que zero. Mensagens de erro nos campos, nada é salvo se estiver inválido. Salvar cria um livro com ID único e volta à lista; Cancelar volta sem criar nada.
- **RF03 — Detalhes:** abre pelo ID passado na rota (`detalhes/{livroId}`), mostra todos os dados (inclusive de livros recém-cadastrados). ID inexistente mostra "Livro não encontrado" sem fechar o app. Tem ação de voltar.
- **RF04 — Sobre/Ajuda:** nome, finalidade, instruções e autor. Botão **Compartilhar aplicativo** abre o seletor do Android com `ACTION_SEND` (Intent implícito); se não houver app compatível, mostra um aviso (sem falhar). Botão Voltar.

## Organização do projeto
```
MainActivity.kt                 única Activity, apenas chama setContent
navigation/Rotas.kt             constantes das 4 rotas
navigation/AppNavigation.kt     NavHost + NavController + callbacks
model/Livro.kt                  modelo do item (id, título, autor, páginas, gênero, resenha)
viewmodel/LivroViewModel.kt     coleção única e compartilhada (mutableStateListOf)
screens/HomeScreen.kt           lista
screens/CadastroScreen.kt       formulário e validações
screens/DetalhesScreen.kt       detalhes do livro
screens/SobreScreen.kt          sobre/ajuda + compartilhamento
ui/theme/BibliotecaTheme.kt     tema Material 3
```
- O `LivroViewModel` é criado em `AppNavigation`, acima dos destinos, e é a única fonte da lista usada por listagem, cadastro e detalhes.
- As telas não recebem o `NavController`: elas só chamam callbacks (`onSalvar`, `onCancelar`, `onVoltar`...) e a navegação é decidida em `AppNavigation`.
- Após salvar/cancelar usa-se `popBackStack()`, então a Home não é empilhada de novo.

## Testes realizados
| Teste | Resultado esperado |
|---|---|
| Abrir livro inicial e voltar | Detalhes corretos e retorno à lista |
| Salvar formulário inválido | Mensagens de erro e nenhum livro criado |
| Cadastrar livro válido e abri-lo | Aparece na lista e os detalhes batem com o digitado |
| Cancelar cadastro | Volta sem criar registro |
| Abrir Sobre e compartilhar | Seletor de compartilhamento do Android abre |
| Voltar repetidamente | Navegação coerente, sem Homes duplicadas |

Também há testes unitários simples do `LivroViewModel` (`app/src/test`).

## Uso de inteligência artificial
Usei o Claude (Anthropic) para planejar a arquitetura e gerar o código inicial do projeto. Depois, [PREENCHER: o que você revisou, testou e alterou por conta própria].

## Limitações conhecidas
- Os livros ficam só em memória: ao encerrar o app por completo, os cadastros novos são perdidos (persistência não era exigida).
- Não há edição nem exclusão de livros.
