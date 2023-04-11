package com.konovalov.compositiongame.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.konovalov.compositiongame.R

class ChooseLevelFragment: Fragment() {
    private lateinit var testButton: Button
    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons(view)
    }

    private fun initButtons(view: View) {
        testButton = view.findViewById<Button?>(R.id.addition_button)
        easyButton= view.findViewById<Button?>(R.id.subtraction_button)
        mediumButton= view.findViewById<Button?>(R.id.multiplication_button)
        hardButton= view.findViewById<Button?>(R.id.division_button)
    }

    override fun onDetach() {
        super.onDetach()
    }
}