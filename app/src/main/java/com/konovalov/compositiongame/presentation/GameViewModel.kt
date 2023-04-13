package com.konovalov.compositiongame.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.konovalov.compositiongame.data.GameRepositoryImpl
import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.usecases.GenerateQuestionUseCase
import com.konovalov.compositiongame.domain.usecases.GetGameSettingsUseCase

class GameViewModel : ViewModel()

private val repository: GameRepositoryImpl = GameRepositoryImpl

private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
private val getGenerateQuestionUseCase = GetGameSettingsUseCase(repository)

private val _gameSettings = MutableLiveData<GameSettings>()
val gameSettings: LiveData<GameSettings>
    get() = _gameSettings

private val _question = MutableLiveData<Question>()
val question: LiveData<Question>
    get() = _question

private val _time = MutableLiveData<Int>()
val time: LiveData<Int>
    get() = _time

private val _countRightAnswers = MutableLiveData<Int>()
val countRightAnswers: LiveData<Int>
    get() = _countRightAnswers

private val _countAllQuestions = MutableLiveData<Int>()
val ccountAllQuestions: LiveData<Int>
    get() = _countAllQuestions