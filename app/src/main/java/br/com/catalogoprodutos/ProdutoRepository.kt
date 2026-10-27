package br.com.catalogoprodutos

import android.content.ContentValues
import android.content.Context

data class Produto(
    val id: Int,
    val nome: String,
    val preco: Double,
    val quantidade: Int
)

class ProdutoRepository(context: Context) {

    private val helper = BancoHelper(context.applicationContext)

    fun inserir(
        nome: String,
        preco: Double,
        quantidade: Int
    ): Boolean {
        val db = helper.writableDatabase

        val valores = ContentValues().apply {
            put("nome", nome)
            put("preco", preco)
            put("quantidade", quantidade)
        }

        val resultado = db.insert(
            "produto",
            null,
            valores
        )

        return resultado != -1L
    }

    fun listar(): List<Produto> {
        val db = helper.readableDatabase
        val produtos = mutableListOf<Produto>()

        val cursor = db.rawQuery(
            "SELECT * FROM produto",
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(
                    it.getColumnIndexOrThrow("id")
                )

                val nome = it.getString(
                    it.getColumnIndexOrThrow("nome")
                )

                val preco = it.getDouble(
                    it.getColumnIndexOrThrow("preco")
                )

                val quantidade = it.getInt(
                    it.getColumnIndexOrThrow("quantidade")
                )

                produtos.add(
                    Produto(
                        id,
                        nome,
                        preco,
                        quantidade
                    )
                )
            }
        }

        return produtos
    }
}
