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
import java.util.logging.Level

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding is equal to null")

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
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
            easyButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.EASY, MathMode.ADDITION)
            }
            normalButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.NORMAL, MathMode.ADDITION)
            }
            hardButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.HARD, MathMode.ADDITION)
            }
            testButton.setOnClickListener {
                launchGameFragment(DifficultyLevel.TEST, MathMode.ADDITION)
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

    companion object {

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}
