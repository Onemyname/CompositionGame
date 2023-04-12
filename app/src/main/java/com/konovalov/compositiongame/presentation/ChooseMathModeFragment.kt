package com.konovalov.compositiongame.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentChooseMathModeBinding
import com.konovalov.compositiongame.databinding.FragmentGameFinishedBinding
import com.konovalov.compositiongame.databinding.FragmentWelcomeBinding

class ChooseMathModeFragment: Fragment()  {
    private  var _binding: FragmentChooseMathModeBinding? = null
    private val binding : FragmentChooseMathModeBinding
    get() = _binding ?: throw RuntimeException("FragmentChooseMathModeBinding is equal to null")

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }
    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseMathModeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : ChooseMathModeFragment{
            return ChooseMathModeFragment()
        }
    }


}