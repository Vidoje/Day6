package com.taurunium.daysix.adapters

import androidx.recyclerview.widget.DiffUtil
import com.taurunium.daysix.models.Exercise

class ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {

    override fun areItemsTheSame(
        oldItem: Exercise,
        newItem: Exercise
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Exercise,
        newItem: Exercise
    ): Boolean {
        return oldItem == newItem
    }
}