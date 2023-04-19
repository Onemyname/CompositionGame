package com.konovalov.compositiongame.data


import com.konovalov.compositiongame.data.NumbersGenerator.generateFirstNumber
import com.konovalov.compositiongame.data.NumbersGenerator.generateSecondNumber
import com.konovalov.compositiongame.data.NumbersGenerator.getAnswerAndOptions
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.repository.GameRepository

object GameRepositoryImpl : GameRepository {
    override fun generateQuestion(
        maxExpressionNumber: Int,
        countOfOptions: Int,
        mathMode: MathMode
    ): Question {
        val firstNumber: Int = generateFirstNumber(maxExpressionNumber, mathMode)
        val secondNumber: Int = generateSecondNumber(firstNumber, maxExpressionNumber, mathMode)
        val answerAndOptions: Pair<Int, List<Int>> =
            getAnswerAndOptions(
                maxExpressionNumber,
                countOfOptions,
                firstNumber,
                secondNumber,
                mathMode
            )

        return Question(firstNumber, secondNumber, answerAndOptions.first, answerAndOptions.second)
    }

    override fun getGameSettings(
        difficultyLevel: DifficultyLevel,
        mathMode: MathMode
    ): GameSettings {

        return when (difficultyLevel) {

            DifficultyLevel.EASY -> GameSettings(
                mathMode,
                25,
                10,
                70,
                60
            )

            DifficultyLevel.NORMAL -> GameSettings(
                mathMode,
                50,
                20,
                80,
                40
            )

            DifficultyLevel.HARD -> GameSettings(
                mathMode,
                99,
                30,
                90,
                40
            )

            DifficultyLevel.TEST -> GameSettings(
                mathMode,
                15,
                3,
                50,
                8
            )
        }
    }
}