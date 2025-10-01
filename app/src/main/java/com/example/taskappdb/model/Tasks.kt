package com.example.taskappdb.model

import java.io.Serializable

data class Task (
    val id_tarefa: Int, val descricao: String, val data: String
) : Serializable