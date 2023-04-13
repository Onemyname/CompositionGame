package com.konovalov.compositiongame.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentChooseLevelBinding
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode

class ChooseLevelFragment : Fragment() {
    private lateinit var mathMode: MathMode

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding is equal to null")

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

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
        binding.apply {
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
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, GameFragment.newInstance(level, mathMode))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onDetach() {
        super.onDetach()
    }

    private fun parseArgs() {
        requireArguments().getParcelable<MathMode>(MATH_MODE)?.let {
            mathMode = it
        }
    }

    companion object {

        private const val MATH_MODE = "mathMode"

        fun newInstance(mathMode: MathMode): ChooseLevelFragment {
            return ChooseLevelFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MATH_MODE, mathMode)
                }
            }
        }
    }
}
