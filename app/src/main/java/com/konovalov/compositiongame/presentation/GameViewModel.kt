@file:Suppress("unused")

package com.konovalov.compositiongame.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.data.GameRepositoryImpl
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.GameResult
import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.usecases.GenerateQuestionUseCase
import com.konovalov.compositiongame.domain.usecases.GetGameSettingsUseCase

class GameViewModel(
    private val application: Application,
    private val difficultyLevel: DifficultyLevel,
    private val mathMode: MathMode
) : ViewModel() {


    private lateinit var gameSettings: GameSettings

    private val repository: GameRepositoryImpl = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswer: LiveData<Int>
        get() = _percentOfRightAnswers

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private val _enoughRightAnswers = MutableLiveData<Boolean>()
    val enoughCount: LiveData<Boolean>
        get() = _enoughRightAnswers

    private val _enoughPercent = MutableLiveData<Boolean>()
    val enoughPercent: LiveData<Boolean>
        get() = _enoughPercent

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private val _timer = MutableLiveData<CountDownTimer>()
    val gameTimer: LiveData<CountDownTimer>
        get() = _timer

    init {
        startGame()
    }

    private fun startGame() {
        getGameSettings()
        if (_timer.value == null) {
            startTimer()
        }
        if (_question.value == null) {
            generateQuestion()
        }
        updateProgress()
    }

    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _percentOfRightAnswers.value = percent
        _progressAnswers.value = String.format(
            application.resources.getString(R.string.progress_answers),
            countOfRightAnswers,
            gameSettings.minCountRightAnswers,
            percent,
            gameSettings.minPercentOfRightAnswers
        )
        _enoughRightAnswers.value = countOfRightAnswers >= gameSettings.minCountRightAnswers
        _enoughPercent.value = percent >= gameSettings.minPercentOfRightAnswers
    }

    private fun calculatePercentOfRightAnswers(): Int {
        if (countOfQuestions == 0) {
            return 0
        }

        return ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    private fun checkAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
    }

    private fun getGameSettings() {
        this.gameSettings = getGameSettingsUseCase(difficultyLevel, mathMode)
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun startTimer() {
        _timer.value = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(milliSec: Long) {
                _formattedTime.value = formatTime(milliSec)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        _timer.value?.start()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(
            gameSettings.maxNumber,
            gameSettings.mathMode
        )
    }

    fun formatTime(milliSec: Long): String {
        val seconds = milliSec / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTE
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTE)

        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    fun finishGame() {
        _gameResult.value = GameResult(
            enoughCount.value == true && enoughPercent.value == true,
            countOfRightAnswers,
            countOfQuestions,
            gameSettings
        )
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }

    private fun stopTimer() {
        _timer.value?.cancel()
    }

    companion object {
        const val MILLIS_IN_SECONDS = 1000L
        const val SECONDS_IN_MINUTE = 60
    }
}