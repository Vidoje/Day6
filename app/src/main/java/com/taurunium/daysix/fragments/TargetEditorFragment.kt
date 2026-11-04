package com.taurunium.daysix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.taurunium.daysix.R
import com.taurunium.daysix.databinding.FragmentTargetEditorBinding
import com.taurunium.daysix.viewmodels.CounterViewModel
import kotlin.getValue

class TargetEditorFragment : Fragment(R.layout.fragment_target_editor) {

    private var _binding: FragmentTargetEditorBinding? = null
    private val binding get() = _binding!!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTargetEditorBinding.bind(view)

        val value = arguments?.getInt("initialTarget")
        binding.etTarget.setText(value.toString())
        initViews()
    }

    private fun initViews(){
        binding.btnSave.setOnClickListener {
            val newTarget = binding.etTarget.text.toString().toIntOrNull()
            if (newTarget == null) {
                binding.etTarget.error = "Enter a whole number"
                return@setOnClickListener
            }

            val nav = findNavController()
            nav.previousBackStackEntry?.savedStateHandle?.set("selectedTarget", newTarget)
            nav.popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }


    }

}