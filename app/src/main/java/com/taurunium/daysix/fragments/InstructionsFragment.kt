package com.taurunium.daysix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.taurunium.daysix.R
import com.taurunium.daysix.databinding.FragmentInstructionsBinding
import com.taurunium.daysix.viewmodels.CounterViewModel
import kotlin.getValue


class InstructionsFragment : Fragment(R.layout.fragment_instructions) {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() =  _binding!!
    private val viewModel: CounterViewModel by navGraphViewModels(
        R.id.workout_graph
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInstructionsBinding.bind(view)
        val repCount = arguments?.getInt("repCount", 0) ?: 0


        binding.txtInstructions.text = "You completed $repCount reps"

        binding.btnUseTarget.setOnClickListener {
            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("selectedTarget", 10)
            navController.popBackStack()
        }

        viewModel.increaseValue()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}