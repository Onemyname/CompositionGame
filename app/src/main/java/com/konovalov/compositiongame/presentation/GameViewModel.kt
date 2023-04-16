package com.konovalov.compositiongame.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.konovalov.compositiongame.data.GameRepositoryImpl
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.usecases.GenerateQuestionUseCase
import com.konovalov.compositiongame.domain.usecases.GetGameSettingsUseCase

class GameViewModel : ViewModel() {


    private lateinit var mathMode: MathMode
    private lateinit var difficultyLevel: DifficultyLevel
    private lateinit var gameSettings: GameSettings
    private val repository: GameRepositoryImpl = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private val _formattedTime = MutableLiveData<String>()
    private var timer : CountDownTimer? = null
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    fun getGameSettings(difficultyLevel: DifficultyLevel, mathMode: MathMode) {
        this.difficultyLevel = difficultyLevel
        this.mathMode = mathMode
        this.gameSettings = getGameSettingsUseCase(difficultyLevel, mathMode)
    }

    private fun generateQuestion() {
        generateQuestionUseCase.invoke(gameSettings.maxExpressionNumber, mathMode)
    }

    fun startGame(difficultyLevel: DifficultyLevel, mathMode: MathMode) {
        getGameSettings(difficultyLevel,mathMode)
        startTimer()
        generateQuestion()
    }

    fun chooseAnswer(id: Int){
        val rightAnswer = question.value
    }

    private fun startTimer() {
        timer = object :
            CountDownTimer(gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS, MILLIS_IN_SECONDS) {
            override fun onTick(milliSec: Long) {
                _formattedTime.value =formatTime(milliSec)
            }

            override fun onFinish() {
                gameFinish()
            }
        }
        timer?.start()
    }

    fun formatTime(milliSec: Long): String {
        val seconds = milliSec / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTE
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTE)

        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    fun gameFinish() {

    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }


    companion object {
        const val MILLIS_IN_SECONDS = 1000L
        const val SECONDS_IN_MINUTE = 60
    }


//    private val _gameSettings = MutableLiveData<GameSettings>()
//    val gameSettings: LiveData<GameSettings>
//        get() = _gameSettings

//

//
//    private val _countRightAnswers = MutableLiveData<Int>()
//    val countRightAnswers: LiveData<Int>
//        get() = _countRightAnswers
//
//    private val _countAllQuestions = MutableLiveData<Int>()
//    val countAllQuestions: LiveData<Int>
//        get() = _countAllQuestions

}