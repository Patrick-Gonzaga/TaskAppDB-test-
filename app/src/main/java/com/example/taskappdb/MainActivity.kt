package com.example.taskappdb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskappdb.adapter.Adapter
import com.example.taskappdb.database.TaskDAO
import com.example.taskappdb.databinding.ActivityMainBinding
import com.example.taskappdb.model.Task

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var taskList = emptyList<Task>()

    private var adapter: Adapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnAdd.setOnClickListener {
                val intent = Intent(applicationContext, NewTaksActivity::class.java)
                startActivity(intent)
            }
        }
        adapter = Adapter({ id -> removeTask(id) }, {task -> editTask(task)})
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)
    }


    fun updateRv(){
        val taskDAO = TaskDAO(applicationContext)
        taskList = taskDAO.list()
        adapter?.addList(taskList)
    }

    fun editTask(task: Task){
        val intent = Intent(this, NewTaksActivity::class.java)
        intent.putExtra("task", task)
        startActivity(intent)
    }

    fun removeTask(id: Int){

        AlertDialog.Builder(this)
            .setMessage("Deseja remover essa tarefa?")
            .setTitle("Alerta")
            .setPositiveButton("Sim") { _,_ ->
                val taskDAO = TaskDAO(this)
                taskDAO.remove(id)
                updateRv()
            }
            .setNegativeButton("Não") {_,_ -> }
            .show()

    }
    override fun onStart() {
        super.onStart()
        updateRv()

    }
}