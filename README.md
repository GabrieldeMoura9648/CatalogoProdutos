# Catálogo de Produtos

Aplicativo Android desenvolvido em Kotlin para a matéria **Programação para Dispositivos Móveis**. O projeto consiste em um catálogo que permite o cadastro e a listagem de produtos utilizando um banco de dados local.

## Funcionalidades

- Cadastro de novos produtos informando nome, preço e quantidade.
- Listagem de todos os produtos cadastrados.
- Armazenamento local de dados utilizando **SQLite**.

## Tecnologias utilizadas

- **Kotlin** — linguagem de programação.
- **Android Studio** — ambiente de desenvolvimento.
- **Jetpack Compose** — construção declarativa da interface.
- **Material 3** — componentes visuais da interface.
- **SQLite** — banco de dados relacional para persistência local.
- **Gradle Kotlin DSL** — configuração e gerenciamento da compilação.

## Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/GabrieldeMoura9648/CatalogoProdutos.git
   ```
2. Abra o Android Studio.
3. Selecione **Open** e escolha a pasta do projeto clonado.
4. Aguarde a sincronização do Gradle.
5. Execute o aplicativo em um emulador ou dispositivo Android compatível.

## Estrutura do projeto

- `app/` — módulo principal do aplicativo Android, contendo a interface (Compose), o repositório de dados e a configuração do banco (`BancoHelper.kt`).
- `gradle/` — arquivos de suporte ao Gradle e ao gerenciamento de versões.
- `build.gradle.kts` e `settings.gradle.kts` — configurações de construção do projeto.

## Contexto acadêmico

Repositório desenvolvido como atividade prática da disciplina **Programação para Dispositivos Móveis**, com foco na criação de interfaces modernas com Jetpack Compose e implementação de persistência de dados locais usando SQLite no Android.

---

Desenvolvido por [Gabriel de Moura](https://github.com/GabrieldeMoura9648).
