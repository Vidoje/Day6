package com.taurunium.daysix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taurunium.daysix.R
import com.taurunium.daysix.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment(R.layout.fragment_instructions) {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() =  _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInstructionsBinding.bind(view)

        binding.txtInstructions.text = getString(R.string.tap_after_each_repetition)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}