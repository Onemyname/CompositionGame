package com.konovalov.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentChooseMathModeBinding
import com.konovalov.compositiongame.domain.entity.MathMode

class ChooseMathModeFragment : Fragment() {
    private var _binding: FragmentChooseMathModeBinding? = null
    private val binding: FragmentChooseMathModeBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseMathModeBinding is equal to null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseMathModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            additionButton.setOnClickListener {
                launchChooseLevelFragment(MathMode.ADDITION)
            }
            subtractionButton.setOnClickListener {
                launchChooseLevelFragment(MathMode.SUBTRACTION)
            }
            multiplicationButton.setOnClickListener {
                launchChooseLevelFragment(MathMode.MULTIPLICATION)
            }
            divisionButton.setOnClickListener {
                launchChooseLevelFragment(MathMode.DIVISION)
            }
        }
    }

    private fun launchChooseLevelFragment(mathMode: MathMode) {
        val args = Bundle().apply {
            putParcelable(ChooseLevelFragment.MATH_MODE, mathMode)
        }
        findNavController().navigate(
            R.id.action_chooseMathModeFragment2_to_chooseLevelFragment2,
            args
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}