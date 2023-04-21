package com.konovalov.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.konovalov.compositiongame.databinding.FragmentChooseLevelBinding
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode

class ChooseLevelFragment : Fragment() {

    private val args by navArgs<ChooseLevelFragmentArgs>()
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding is equal to null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            easyLevelButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.EASY, args.mathMode)
            }
            normalLevelButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.NORMAL, args.mathMode)
            }
            hardLevelButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.HARD, args.mathMode)
            }
            testLevelButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.TEST, args.mathMode)
            }
        }
    }

    private fun launchGameFragment(level: DifficultyLevel, mathMode: MathMode) {
        findNavController().navigate(
            ChooseLevelFragmentDirections
                .actionChooseLevelFragmentToGameFragment(level, mathMode)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
