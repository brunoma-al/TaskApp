package com.bruno.android.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bruno.android.taskapp.R
import com.bruno.android.taskapp.databinding.FragmentLoginBinding
import com.bruno.android.taskapp.databinding.FragmentSplashBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {

        binding.btnLogin.setOnClickListener() {
            validateData()
            //findNavController().navigate(R.id.action_global_homeFragment)
        }
        binding.btnRegister.setOnClickListener() {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }

        binding.btnRecover.setOnClickListener() {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }
    }

    private fun validateData() {
        val email = binding.editEmail.text.toString().trim()
        val senha = binding.editSenha.text.toString().trim()

        if (email.isNotEmpty()) {
            if (senha.isNotEmpty()) {
                findNavController().navigate(R.id.action_global_homeFragment)
            } else {
                Toast.makeText(requireContext(), "Preencha sua senha!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Preencha seu email!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}