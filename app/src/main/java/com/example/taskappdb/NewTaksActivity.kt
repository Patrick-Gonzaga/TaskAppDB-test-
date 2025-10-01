package com.example.taskappdb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskappdb.database.TaskDAO
import com.example.taskappdb.databinding.ActivityNewTaksBinding
import com.example.taskappdb.model.Task

class NewTaksActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityNewTaksBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var task: Task? = null
        val bundle = intent.extras

        if (bundle != null) {
            binding.titleNewTask.text = "Atualizar Tarefa"
            task = bundle.getSerializable("task") as Task
            binding.inputNewTask.setText(task.descricao)
        }


        with(binding) {
            btnSaveTask.setOnClickListener {
                if (inputNewTask.text?.isNotEmpty() == true) {

                    if (task == null) {
                        save()
                    } else {
                        edit(task)
                    }

                } else {
                    Toast.makeText(applicationContext, "Preencha uma tarefa", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }

    }

    fun edit(task: Task) {
        val taskDAO = TaskDAO(applicationContext)
        val desc = binding.inputNewTask.text.toString()

        val newTask = Task(task.id_tarefa, desc, "default")

        if (taskDAO.update(newTask)) {
            Toast.makeText(applicationContext, "Tarefa editada com sucesso", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }

    fun save() {
        val task = Task(
            -1, binding.inputNewTask.text.toString(), "default"
        )
        val taskDAO = TaskDAO(applicationContext)
        if (taskDAO.save(task)) {
            Toast.makeText(applicationContext, "Tarefa salva com sucesso", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }
}