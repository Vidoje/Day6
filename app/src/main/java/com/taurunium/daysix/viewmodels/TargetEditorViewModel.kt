package com.taurunium.daysix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class TargetEditorViewModel(
    private val state: SavedStateHandle
): ViewModel() {
    val draft: LiveData<Int> = state.getLiveData("draft", state.get<Int>("initialTarget") ?: 0)

    fun updateDraft(value: Int){
        state["draft"] = value
    }
}