package com.bruno.android.taskapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bruno.android.taskapp.R
import com.bruno.android.taskapp.databinding.FragmentHomeBinding
import com.bruno.android.taskapp.databinding.FragmentLoginBinding
import com.bruno.android.taskapp.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabs()
    }

    private fun initTabs(){

        val pagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager2.adapter = pagerAdapter

        pagerAdapter.addFragment(TodoFragment(), R.string.status_task_todo)
        pagerAdapter.addFragment(DoingFragment(), R.string.status_task_doing)
        pagerAdapter.addFragment(DoneFragment(), R.string.status_task_done)

        binding.viewPager2.offscreenPageLimit = pagerAdapter.itemCount

        TabLayoutMediator(binding.tabLayout, binding.viewPager2){tab, position ->
            tab.text = getString(pagerAdapter.getTitle(position))
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}