package com.bruno.android.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bruno.android.taskapp.R
import com.bruno.android.taskapp.databinding.FragmentFormTaskBinding
import com.bruno.android.taskapp.util.initToolbar

class FormTaskFragment : Fragment() {

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
    }

    private fun initListener() {

        binding.btnSave.setOnClickListener() {
            validateData()
        }
    }

    private fun validateData() {
        val description = binding.editDescription.text.toString().trim()

        if (description.isNotEmpty()) {
            Toast.makeText(requireContext(), "Tudo certo!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Preencha seu email!", Toast.LENGTH_SHORT).show()
        }
    }

}