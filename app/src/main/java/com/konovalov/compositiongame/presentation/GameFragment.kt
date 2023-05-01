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
import androidx.navigation.fragment.navArgs
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentGameBinding
import com.konovalov.compositiongame.domain.entity.GameResult
import com.konovalov.compositiongame.domain.entity.MathMode


class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val gameViewModelFactory by lazy {
        GameViewModelFactory(args.difficultyLevel, args.mathMode, requireActivity().application)
    }
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, gameViewModelFactory)[GameViewModel::class.java]
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding is equal to null")


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
        binding.viewModel = viewModel
        binding.mathMode = args.mathMode
        binding.lifecycleOwner = viewLifecycleOwner
        observeGameViewModel()
    }

    private fun observeGameViewModel() {
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections
                .actionGameFragmentToGameFinishedFragment(gameResult)
        )
    }
}