package com.example.taskappdb.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.taskappdb.model.Task

class TaskDAO(context: Context) : ITaskDAO {

    private val leitura = DatabaseHelper(context).readableDatabase
    private val escrita = DatabaseHelper(context).writableDatabase

    override fun save(task: Task): Boolean {
        val values = ContentValues()
        values.put(DatabaseHelper.COLUMN_DESCRICAO, task.descricao)

        try {
            escrita.insert(DatabaseHelper.TABLE_NAME,null, values)
            Log.i("LOG_DB", "Sucesso ao adicionar task")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("LOG_DB", "Erro ao adicionar task: ${e.message.toString()}")
            return false
        }
        return true
    }

    override fun update(task: Task): Boolean {

        val values = ContentValues()
        values.put(DatabaseHelper.COLUMN_DESCRICAO, task.descricao)
        val args = arrayOf(task.id_tarefa.toString())

        escrita.update(DatabaseHelper.TABLE_NAME, values, "${DatabaseHelper.COLUMN_ID} = ?",args)

        return true
    }

    override fun remove(idTask: Int): Boolean {
            val args = arrayOf(idTask.toString())
        try {
            escrita.delete(DatabaseHelper.TABLE_NAME, "${DatabaseHelper.COLUMN_ID} = ?", args)
            Log.i("LOG_DB", "Sucesso ao remover task")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("LOG_DB", "Erro ao remover task: ${e.message.toString()}")
            return false
        }
        return true

    }

    override fun list(): List<Task> {
        val taskList = mutableListOf<Task>()

        val sql = "SELECT ${DatabaseHelper.COLUMN_ID}, " +
                "${DatabaseHelper.COLUMN_DESCRICAO}, " +
                "    strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.COLUMN_DATA}) ${DatabaseHelper.COLUMN_DATA} " +
                "FROM ${DatabaseHelper.TABLE_NAME}"


        val cursor = leitura.rawQuery(sql, null)

        val indexID = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)
        val indexDESC = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRICAO)
        val indexDATE = cursor.getColumnIndex(DatabaseHelper.COLUMN_DATA)

        while (cursor.moveToNext()){
            val getId = cursor.getInt(indexID)
            val getDesc = cursor.getString(indexDESC)
            val getDate = cursor.getString(indexDATE)

            taskList.add(
                Task(getId, getDesc, getDate)
            )
        }

        return taskList
    }
}