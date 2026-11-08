package com.taurunium.daysix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taurunium.daysix.models.Exercise

class HomeViewModel: ViewModel() {

    private val _exercises = MutableLiveData(
        listOf(
            Exercise(1L, "Squats", 10),
            Exercise(2L, "Push-ups", 12),
            Exercise(3L, "Lunges", 8)
        )
    )

    val exercises: LiveData<List<Exercise>> = _exercises
    private var nextExerciseId = 4L

    fun updateTarget(exerciseId: Long, newTarget: Int) {
        _exercises.value = _exercises.value.orEmpty().map { exercise ->
            if (exercise.id == exerciseId) {
                exercise.copy(targetReps = newTarget)
            } else {
                exercise
            }
        }
    }


    fun addExercise(name: String, targetReps: Int) {
        val exercise = Exercise(
            id = nextExerciseId++,
            name = name,
            targetReps = targetReps
        )

        _exercises.value = _exercises.value.orEmpty() + exercise
    }

    fun removeExercise(exerciseId: Long) {
        _exercises.value = _exercises.value.orEmpty().filter { exercise ->
            exercise.id != exerciseId
        }
    }
}