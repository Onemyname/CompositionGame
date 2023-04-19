package com.konovalov.compositiongame.data


import com.konovalov.compositiongame.data.NumbersGenerator.dividend
import com.konovalov.compositiongame.data.NumbersGenerator.divisor
import com.konovalov.compositiongame.data.NumbersGenerator.firstSummand
import com.konovalov.compositiongame.data.NumbersGenerator.getAnswerAndOptions
import com.konovalov.compositiongame.data.NumbersGenerator.minuend
import com.konovalov.compositiongame.data.NumbersGenerator.multiplicand
import com.konovalov.compositiongame.data.NumbersGenerator.multiplier
import com.konovalov.compositiongame.data.NumbersGenerator.secondSummand
import com.konovalov.compositiongame.data.NumbersGenerator.subtrahend
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.repository.GameRepository
import com.konovalov.compositiongame.domain.entity.MathMode.ADDITION as ADD
import com.konovalov.compositiongame.domain.entity.MathMode.DIVISION as DIV
import com.konovalov.compositiongame.domain.entity.MathMode.MULTIPLICATION as MUL
import com.konovalov.compositiongame.domain.entity.MathMode.SUBTRACTION as SUB

object GameRepositoryImpl : GameRepository {
    override fun generateQuestion(
        maxExpressionNumber: Int,
        countOfOptions: Int,
        mathMode: MathMode
    ): Question {
        val firstNumber: Int = when (mathMode) {
            ADD -> firstSummand(maxExpressionNumber)
            SUB -> minuend(maxExpressionNumber)
            MUL -> multiplicand(maxExpressionNumber)
            DIV -> dividend(maxExpressionNumber)
        }

        val secondNumber: Int = when (mathMode) {
            ADD -> secondSummand(maxExpressionNumber, firstNumber)
            SUB -> subtrahend(firstNumber)
            MUL -> multiplier(maxExpressionNumber, firstNumber)
            DIV -> divisor(firstNumber)
        }

        val rightAnswerAndOptions: Pair<Int, List<Int>> =
            getAnswerAndOptions(
                maxExpressionNumber,
                countOfOptions,
                firstNumber,
                secondNumber,
                mathMode
            )
        val rightAnswer = rightAnswerAndOptions.first
        val options = rightAnswerAndOptions.second

        return Question(firstNumber, secondNumber, rightAnswer, options)
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
                100,
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