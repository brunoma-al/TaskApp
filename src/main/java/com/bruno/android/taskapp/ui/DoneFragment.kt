package com.bruno.android.taskapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruno.android.taskapp.R
import com.bruno.android.taskapp.data.model.Status
import com.bruno.android.taskapp.data.model.Task
import com.bruno.android.taskapp.databinding.FragmentDoingBinding
import com.bruno.android.taskapp.databinding.FragmentDoneBinding
import com.bruno.android.taskapp.ui.adapter.TaskAdapter

class DoneFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(getTasks())
    }

    private fun initRecyclerView(taskList: List<Task>){
        taskAdapter = TaskAdapter(requireContext(), taskList) {task, option ->
            optionSelected(task, option)
        }

        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.adapter = taskAdapter
    }

    private fun optionSelected(task: Task, option: Int){
        when(option) {
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Back ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_REMOVE -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTasks() = listOf<Task>(
        Task("0", "Criar nova tela do app", Status.DONE),
        Task("1", "Validar informações na tela de login", Status.DONE),
        Task("2", "Adicionar nova funcionalidade no app", Status.DONE),
        Task("3", "Salvar token no localmente", Status.DONE),
        Task("4", "Criar funcionalidade de logout no app", Status.DONE)
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}