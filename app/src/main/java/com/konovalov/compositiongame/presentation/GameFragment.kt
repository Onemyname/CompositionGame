package com.konovalov.compositiongame.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentGameBinding
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.GameResult
import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.MathMode

class GameFragment: Fragment() {

    private lateinit var difficultyLevel: DifficultyLevel
    private lateinit var mathMode: MathMode

    private  var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
    get() = _binding ?: throw RuntimeException("FragmentGameBinding is equal to null")
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
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener{
            launchGameFinishedFragment(GameResult(true,1,1,
                GameSettings(1,1,1,1)))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
    }
    private fun parseArgs(){
        difficultyLevel = requireArguments().getSerializable(KEY_LEVEL) as DifficultyLevel
        mathMode = requireArguments().getSerializable(MATH_MODE) as MathMode
    }

    private  fun launchGameFinishedFragment(gameResult: GameResult){
        val fragmentManager = requireActivity().supportFragmentManager
        val current = fragmentManager.findFragmentById(R.id.main_fragment_container)
        fragmentManager.popBackStack(current!!.tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object{

        private const val KEY_LEVEL = "level"
        private const val MATH_MODE = "mathMode"

        fun newInstance(difficultyLevel: DifficultyLevel, mathMode: MathMode) : GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL,difficultyLevel)
                    putSerializable(MATH_MODE,mathMode)
                }
            }
        }
    }
}