@file:Suppress("DEPRECATION")

package com.konovalov.compositiongame.presentation

import android.media.MediaFormat.KEY_LEVEL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentChooseLevelBinding
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode

class ChooseLevelFragment : Fragment() {
    private lateinit var mathMode: MathMode

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding is equal to null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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
                launchGameFragment(DifficultyLevel.EASY, mathMode)
            }
            normalLevelButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.NORMAL, mathMode)
            }
            hardLevelButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.HARD, mathMode)
            }
            testLevelButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.TEST, mathMode)
            }
        }
    }

    private fun launchGameFragment(level: DifficultyLevel, mathMode: MathMode) {
        val args = Bundle().apply {
            putParcelable(KEY_LEVEL, level)
            putParcelable(MATH_MODE, mathMode)
        }
        findNavController().navigate(R.id.action_chooseLevelFragment2_to_gameFragment2, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<MathMode>(MATH_MODE)?.let {
            mathMode = it
        }
    }

    companion object {

        const val MATH_MODE = "mathMode"
    }
}
