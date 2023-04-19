package com.konovalov.compositiongame.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode

class GameViewModelFactory(
    private val level: DifficultyLevel,
    private val mathMode: MathMode,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(application,level,mathMode) as T
        }
        else{
            throw RuntimeException("Unknown view model class $modelClass for GameViewModelFactory")
        }
    }
}