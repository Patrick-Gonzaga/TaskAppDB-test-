package com.example.taskappdb.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappdb.NewTaksActivity
import com.example.taskappdb.databinding.ItemRvBinding
import com.example.taskappdb.model.Task


class Adapter(
    val onRemove: (Int) -> Unit,
    val onEdit: (task:Task) -> Unit
): RecyclerView.Adapter<Adapter.TaskViewHolder>(){
    private var taskList: List<Task> = emptyList<Task>()

    fun addList(list: List<Task>){
        this.taskList = list
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(
        itemBinding: ItemRvBinding
    ): RecyclerView.ViewHolder(itemBinding.root){

        private val binding: ItemRvBinding

        init {
            binding = itemBinding
        }

        fun bind(task: Task){
            binding.textTask.text = task.descricao
            binding.textDate.text = task.data
            binding.btnRemove.setOnClickListener {
                onRemove(task.id_tarefa)
            }
            binding.btnEdit.setOnClickListener {
                onEdit(task)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter.TaskViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvBinding.inflate(inflater, parent, false)

        return TaskViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: Adapter.TaskViewHolder,
        position: Int
    ) {

        val task = taskList[position]
        holder.bind(task)

    }

    override fun getItemCount(): Int = taskList.size


    fun updateData(newList: List<Task>){
        notifyDataSetChanged()
    }

}

/*
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappdb.database.TaskDAO
import com.example.taskappdb.databinding.ItemRvBinding
import com.example.taskappdb.model.Task
import kotlin.coroutines.coroutineContext

class Adapter(
    val onClickRemove: (Int) -> Unit
) : RecyclerView.Adapter<Adapter.TaskViewHolder>() {

    private var taskList: List<Task> = emptyList()

    fun addList(list: List<Task>) {
        this.taskList = list
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

            private val binding: ItemRvBinding

        init {
            binding = itemBinding
        }

        fun bind(task: Task){
            binding.textTask.text = task.descricao
            binding.textDate.text = task.data
            binding.btnRemove.setOnClickListener {
                onClickRemove(task.id_tarefa)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvBinding.inflate(inflater, parent, false)

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = taskList.size

    fun updateData(newList: List<Task>) {
        notifyDataSetChanged()

    }

}*/
