package com.bruno.android.taskapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bruno.android.taskapp.R
import com.bruno.android.taskapp.data.model.Status
import com.bruno.android.taskapp.data.model.Task
import com.bruno.android.taskapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>,
    private val taskSelected: (Task, Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    companion object {
        val SELECT_BACK: Int = 1
        val SELECT_REMOVE: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private fun setIndicators(task: Task, holder: MyViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.btnBack.isVisible = false

                holder.binding.btnDelete.setOnClickListener {taskSelected(task, SELECT_REMOVE)}
                holder.binding.btnEdit.setOnClickListener {taskSelected(task, SELECT_EDIT)}
                holder.binding.btnDetails.setOnClickListener {taskSelected(task, SELECT_DETAILS)}
                holder.binding.btnNext.setOnClickListener {taskSelected(task, SELECT_NEXT)}
            }

            Status.DOING -> {
                holder.binding.btnBack.setColorFilter(ContextCompat.getColor(context, R.color.color_status_todo))
                holder.binding.btnNext.setColorFilter(ContextCompat.getColor(context, R.color.color_status_done))

                holder.binding.btnBack.setOnClickListener{taskSelected(task, SELECT_BACK)}
                holder.binding.btnDelete.setOnClickListener {taskSelected(task, SELECT_REMOVE)}
                holder.binding.btnEdit.setOnClickListener {taskSelected(task, SELECT_EDIT)}
                holder.binding.btnDetails.setOnClickListener {taskSelected(task, SELECT_DETAILS)}
                holder.binding.btnNext.setOnClickListener {taskSelected(task, SELECT_NEXT)}
            }

            Status.DONE -> {
                holder.binding.btnNext.isVisible = false

                holder.binding.btnBack.setOnClickListener{taskSelected(task, SELECT_BACK)}
                holder.binding.btnDelete.setOnClickListener {taskSelected(task, SELECT_REMOVE)}
                holder.binding.btnEdit.setOnClickListener {taskSelected(task, SELECT_EDIT)}
                holder.binding.btnDetails.setOnClickListener {taskSelected(task, SELECT_DETAILS)}
            }
        }
    }

    override fun getItemCount() = taskList.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = taskList[position]

        holder.binding.textDescription.text = task.description

        setIndicators(task, holder)
    }

    inner class MyViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}