package br.com.catalogoprodutos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE produto (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                preco REAL NOT NULL,
                quantidade INTEGER NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Tratar mudanças na estrutura quando a versão do banco for incrementada.
    }

    companion object {
        private const val DATABASE_NAME = "catalogo.db"
        private const val DATABASE_VERSION = 1
    }
}
