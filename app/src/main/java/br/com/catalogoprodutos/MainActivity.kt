package br.com.catalogoprodutos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ProdutoRepository(applicationContext)

        setContent {
            MaterialTheme {
                TelaProdutos(repository)
            }
        }
    }
}

@Composable
private fun TelaProdutos(repository: ProdutoRepository) {
    var nome by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }
    var produtos by remember { mutableStateOf(repository.listar()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cadastro de Produtos",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do produto") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = preco,
            onValueChange = { preco = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val nomeValidado = nome.trim()
                val precoValidado = preco.trim().replace(",", ".").toDoubleOrNull()
                val quantidadeValidada = quantidade.trim().toIntOrNull()

                if (
                    nomeValidado.isEmpty() ||
                    precoValidado == null ||
                    precoValidado < 0 ||
                    quantidadeValidada == null ||
                    quantidadeValidada < 0
                ) {
                    mensagem = "Confira os dados informados."
                } else {
                    val sucesso = repository.inserir(
                        nomeValidado,
                        precoValidado,
                        quantidadeValidada
                    )

                    if (sucesso) {
                        nome = ""
                        preco = ""
                        quantidade = ""
                        produtos = repository.listar()
                        mensagem = "Produto cadastrado!"
                    } else {
                        mensagem = "Não foi possível cadastrar o produto."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar produto")
        }

        if (mensagem.isNotEmpty()) {
            Text(
                text = mensagem,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Produtos cadastrados",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn {
            items(items = produtos, key = { it.id }) { produto ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = produto.nome,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = String.format(
                            Locale.forLanguageTag("pt-BR"),
                            "Preço: R$ %.2f | Estoque: %d",
                            produto.preco,
                            produto.quantidade
                        )
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
