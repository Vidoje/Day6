package com.taurunium.daysix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CounterViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val counter: LiveData<Int> =
        savedStateHandle.getLiveData(KEY_COUNTER, 0)

    val target: LiveData<Int> =
        savedStateHandle.getLiveData(KEY_TARGET, 0)

    fun increaseValue() {
        savedStateHandle[KEY_COUNTER] = (counter.value ?: 0) + 1
    }

    fun resetValue() {
        savedStateHandle[KEY_COUNTER] = 0
    }

    fun setTarget(value: Int) {
        savedStateHandle[KEY_TARGET] = value
    }


    companion object {
        private const val KEY_COUNTER = "counter"
        private const val KEY_TARGET = "target"
    }
}