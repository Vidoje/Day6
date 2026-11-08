package com.taurunium.daysix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taurunium.daysix.databinding.ItemExerciseBinding
import com.taurunium.daysix.models.Exercise

class ExerciseAdapter(
    private val onExerciseClick: (Exercise) -> Unit,
    private val onDeleteClick: (Exercise) -> Unit
): ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder>(ExerciseDiffCallback()) {

    class ExerciseViewHolder(
        private val binding: ItemExerciseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise, onExerciseClick: (Exercise) -> Unit, onDeleteClick: (Exercise) -> Unit) {
            binding.txtName.text = exercise.name
            binding.txtTarget.text = "Target: ${exercise.targetReps} reps"

            binding.root.setOnClickListener {
                onExerciseClick(exercise)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(exercise)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExerciseViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position), onExerciseClick, onDeleteClick)
    }

}