package com.taurunium.daysix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.taurunium.daysix.R
import com.taurunium.daysix.databinding.FragmentCounterBinding
import com.taurunium.daysix.viewmodels.CounterViewModel


class CounterFragment : Fragment(R.layout.fragment_counter) {

    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CounterViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCounterBinding.bind(view)

        initViews()
    }

    private fun initViews(){
        binding.btnPlus.setOnClickListener {
            viewModel.increaseValue()
        }

        binding.btnReset.setOnClickListener {
            viewModel.resetValue()
        }

        binding.btnInstructions.setOnClickListener {
            findNavController().navigate(R.id.action_counterFragment_to_instructionsFragment)
        }

        viewModel.counter.observe(viewLifecycleOwner){counter->
            binding.txtCounter.text = counter.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}