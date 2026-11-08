package com.taurunium.daysix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taurunium.daysix.R
import com.taurunium.daysix.adapters.ExerciseAdapter
import com.taurunium.daysix.databinding.FragmentHomeBinding
import com.taurunium.daysix.viewmodels.HomeViewModel
import kotlin.getValue


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.btnStartWorkout.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_workout_graph
            )
        }


        binding.rvExercises.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ExerciseAdapter (
            onExerciseClick = { exercise ->
                val args = Bundle().apply {
                    putString("exerciseName", exercise.name)
                    putInt("initialTarget", exercise.targetReps)
                }
                findNavController().navigate(
                    R.id.action_homeFragment_to_workout_graph,
                    args
                )
            },
            onDeleteClick = { exercise ->
                    viewModel.removeExercise(exercise.id)
                }
        )

        binding.rvExercises.adapter = adapter

        viewModel.exercises.observe(viewLifecycleOwner) { exercises ->
            adapter.submitList(exercises)
        }

        binding.btnUpdateTarget.setOnClickListener {
            viewModel.updateTarget(exerciseId = 2L, newTarget = 15)
        }

        binding.btnAddExercise.setOnClickListener {
            viewModel.addExercise(
                name = "Sit-ups",
                targetReps = 20
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}