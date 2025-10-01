package com.example.taskappdb.database

import com.example.taskappdb.model.Task

interface ITaskDAO {
    fun save(task: Task): Boolean
    fun update(task: Task): Boolean
    fun remove(idTask: Int): Boolean
    fun list(): List<Task>
}