@file:Suppress("DEPRECATION")

package com.konovalov.compositiongame.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentGameBinding
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.GameResult
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.presentation.ChooseLevelFragment.Companion.MATH_MODE
import com.konovalov.compositiongame.presentation.GameFinishedFragment.Companion.GAME_RESULT

class GameFragment : Fragment() {

    private lateinit var difficultyLevel: DifficultyLevel
    private lateinit var mathMode: MathMode

    private val tvOptions by lazy {
        mutableListOf<Button>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    private val gameViewModelFactory by lazy {
        GameViewModelFactory(difficultyLevel, mathMode, requireActivity().application)
    }
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, gameViewModelFactory)[GameViewModel::class.java]
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding is equal to null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arithmeticOperationSign.text = setSign(mathMode)
        observeGameViewModel()
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeGameViewModel() {
        viewModel.question.observe(viewLifecycleOwner) {
            binding.firstNumberText.text = it.firstNumber.toString()
            binding.secondNumberText.text = it.secondNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentOfRightAnswer.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        viewModel.enoughCount.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.tvAnswersProgress.setTextColor(color)
        }
        viewModel.enoughPercent.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
        }
    }

    private fun getColorByState(it: Boolean): Int {
        val colorResId = if (it) R.color.forest_green else android.R.color.holo_red_light

        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun setSign(mathMode: MathMode): String {
        return when (mathMode) {
            MathMode.ADDITION -> resources.getString(R.string.addition)
            MathMode.SUBTRACTION -> resources.getString(R.string.subtraction)
            MathMode.MULTIPLICATION -> resources.getString(R.string.multiplication)
            MathMode.DIVISION -> resources.getString(R.string.division)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun parseArgs() {
        with(requireArguments()) {
            getParcelable<DifficultyLevel>(KEY_LEVEL)?.let {
                difficultyLevel = it
            }
            getParcelable<MathMode>(MATH_MODE)?.let {
                mathMode = it
            }
        }

    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        val args = Bundle().apply {
            putParcelable(GAME_RESULT, gameResult)
        }
        findNavController().navigate(R.id.action_gameFragment2_to_gameFinishedFragment2, args)
    }

    companion object {

        private const val KEY_LEVEL = "level"
    }
}