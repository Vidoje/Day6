package com.taurunium.daysix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taurunium.daysix.R
import com.taurunium.daysix.adapters.ExerciseAdapter
import com.taurunium.daysix.databinding.FragmentHomeBinding
import com.taurunium.daysix.models.Exercise


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.btnStartWorkout.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_workout_graph
            )
        }


        binding.rvExercises.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ExerciseAdapter { exercise ->
            val args = Bundle().apply {
                putString("exerciseName", exercise.name)
                putInt("initialTarget", exercise.targetReps)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_workout_graph,
                args
            )
        }

        binding.rvExercises.adapter = adapter

        adapter.submitList(
            listOf(
                Exercise(1L, "Squats", 10),
                Exercise(2L, "Push-ups", 12),
                Exercise(3L, "Lunges", 8)
            )
        )

        binding.btnUpdateTarget.setOnClickListener {
            val updatedExercises = adapter.currentList.map { exercise ->
                if (exercise.id == 2L) {
                    exercise.copy(targetReps = 15)
                } else {
                    exercise
                }
            }

            adapter.submitList(updatedExercises)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}