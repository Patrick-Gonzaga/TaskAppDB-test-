package com.example.taskappdb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, NAME_DB, null, VERSION_DB
) {
    companion object{
        val NAME_DB = "Task.db"
        val VERSION_DB = 1
        val TABLE_NAME = "tarefas"
        val COLUMN_ID = "id_tarefa"
        val COLUMN_DESCRICAO = "descricao"
        val COLUMN_DATA = "data_hora"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
                  "$COLUMN_ID INTEGER not NULL PRIMARY KEY AUTOINCREMENT," +
                  "$COLUMN_DESCRICAO VARCHAR(70)," +
                  "$COLUMN_DATA DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                  ");"

        try {
            db?.execSQL(sql)
            Log.i("LOG_DB", "Sucesso ao criar o banco de dados")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("LOG_DB", "Erro ao criar banco de dados: ${e.message.toString()}")
        }


    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }


}