package com.konovalov.compositiongame.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.databinding.FragmentWelcomeBinding

class WelcomeFragment: Fragment() {

    private  var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
    get()= _binding ?: throw RuntimeException("FragmentWelcomeBinding is equal to null")


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener{
            launchChooseLevelFragment()
//          launchChooseMathModeFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
    }


//    private fun launchChooseMathModeFragment(){
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_fragment_container, ChooseMathModeFragment.newInstance())
//            .addToBackStack(null)
//            .commit()
//    }
    private fun launchChooseLevelFragment(){
    val fragmentManager = requireActivity().supportFragmentManager
    val current = fragmentManager.findFragmentById(R.id.main_fragment_container)
    fragmentManager.popBackStack(current!!.tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, ChooseLevelFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }


    companion object{
        fun newInstance() : WelcomeFragment{
            return WelcomeFragment()
        }
    }

}