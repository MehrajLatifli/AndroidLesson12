package com.example.androidlesson12.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson12.Models.Todo
import com.example.androidlesson12.databinding.ItemTodoBinding

class TodoAdapter  : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todoList = arrayListOf<Todo>()

    lateinit var onClickItem: (String) -> Unit

    inner class TodoViewHolder(val itemTodoBinding: ItemTodoBinding) :
        RecyclerView.ViewHolder(itemTodoBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val Todo = todoList[position]

        holder.itemTodoBinding.textView.text = Todo.todo

       holder.itemTodoBinding.materialCardView.setOnClickListener {

            onClickItem.invoke(Todo.id.toString())
        }


    }

    fun updateList(newList: ArrayList<Todo>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()

    }
}