package com.konovalov.compositiongame.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentGameFinishedBinding
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.GameResult
import com.konovalov.compositiongame.domain.entity.MathMode

class GameFinishedFragment: Fragment()  {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
    get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is equal to null")
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
    ): View{
        _binding = FragmentGameFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener{
            launchWelcomeFragment()
        }
    }

    private fun launchWelcomeFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val current = fragmentManager.findFragmentById(R.id.main_fragment_container)
        fragmentManager.popBackStack(current!!.tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        fragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container,WelcomeFragment.newInstance())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun parseArgs(){
        gameResult = requireArguments().getSerializable(GAME_RESULT) as GameResult

    }
    companion object{

        private const val GAME_RESULT = "gameResult"

        fun newInstance(gameResult: GameResult) : GameFinishedFragment{
            return GameFinishedFragment().apply{
                arguments = Bundle().apply {
                    putSerializable(GAME_RESULT,gameResult)
                }
            }
        }
    }
}