package com.taurunium.daysix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.taurunium.daysix.R
import com.taurunium.daysix.databinding.FragmentCounterBinding
import com.taurunium.daysix.viewmodels.CounterViewModel


class CounterFragment : Fragment(R.layout.fragment_counter) {

    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CounterViewModel  by navGraphViewModels(
        R.id.workout_graph
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCounterBinding.bind(view)

        initViews()

        val resultHandle = findNavController()
            .getBackStackEntry(R.id.counterFragment).savedStateHandle

        resultHandle.getLiveData<Int>("selectedTarget")
            .observe(viewLifecycleOwner) { target ->
                viewModel.setTarget(target)
                resultHandle.remove<Int>("selectedTarget")
            }

    }

    private fun initViews(){
        binding.btnPlus.setOnClickListener {
            viewModel.increaseValue()
        }

        binding.btnReset.setOnClickListener {
            viewModel.resetValue()
        }

        binding.btnInstructions.setOnClickListener {
            val args = Bundle().apply{
                putInt("repCount", viewModel.counter.value?:0)
            }

            findNavController().navigate(R.id.action_counterFragment_to_instructionsFragment, args)
        }

        viewModel.counter.observe(viewLifecycleOwner){counter->
            binding.txtCounter.text = counter.toString()
        }

        viewModel.target.observe(viewLifecycleOwner) { target ->
            binding.txtTarget.text = "Target: $target reps"
        }

        viewModel.exerciseName.observe(viewLifecycleOwner) { name ->
            binding.txtExerciseName.text = name
        }

        binding.btnFinishWorkout.setOnClickListener {
            findNavController().popBackStack(
                R.id.workout_graph,
                true
            )
        }

        binding.btnEditTarget.setOnClickListener {
            val currentTarget = viewModel.target.value ?: 0
            val args = Bundle().apply {
                putInt("initialTarget", currentTarget)
            }

            findNavController().navigate(
                R.id.action_counterFragment_to_targetEditorFragment,
                args
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


