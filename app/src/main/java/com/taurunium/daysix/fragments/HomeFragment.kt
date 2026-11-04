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

        val exercises = listOf(
            Exercise("Squats", 10),
            Exercise("Push-ups", 12),
            Exercise("Lunges", 8)
        )

        binding.rvExercises.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExercises.adapter = ExerciseAdapter(exercises) { exercise ->
            val args = Bundle().apply {
                putString("exerciseName", exercise.name)
                putInt("initialTarget", exercise.targetReps)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_workout_graph,
                args
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}