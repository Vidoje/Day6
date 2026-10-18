package com.taurunium.daysix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter


    private val _target = MutableLiveData(0)
    val target: LiveData<Int> = _target

    fun increaseValue(){
        _counter.value = (_counter.value ?: 0) + 1
    }

    fun resetValue(){
        _counter.value = 0
    }

    fun restoreCounter(counterValue: Int) {
        _counter.value = counterValue
    }

    fun setTarget(value: Int) {
        _target.value = value
    }
}