package com.konovalov.compositiongame.data

import com.konovalov.compositiongame.data.numbersGenerators.*
import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.MathMode.ADDITION as ADD
import com.konovalov.compositiongame.domain.entity.MathMode.SUBTRACTION as SUB
import com.konovalov.compositiongame.domain.entity.MathMode.MULTIPLICATION as MULT
import com.konovalov.compositiongame.domain.entity.MathMode.DIVISION as DIV
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.repository.GameRepository

object GameRepositoryImpl : GameRepository {

    override fun generateQuestion(
        maxExpressionNumber: Int,
        countOfOptions: Int,
        mathMode: MathMode
    ): Question {
        val firstNumber: Int = when (mathMode) {
            ADD -> generateFirstSummand(maxExpressionNumber)
            SUB -> generateMinuend(maxExpressionNumber)
            MULT -> generateMultiplicanda(maxExpressionNumber)
            DIV -> generateDividend(maxExpressionNumber)
        }

        val secondNumber: Int = when (mathMode) {
            ADD -> generateSecondSummand(maxExpressionNumber, firstNumber)
            SUB -> generateSubtrahend(firstNumber)
            MULT -> generateMultiplier(maxExpressionNumber, firstNumber)
            DIV -> generateDivisior(firstNumber)
        }

        val answers: List<Int> = when (mathMode) {
            ADD -> generateSumAnswers(
                maxExpressionNumber,
                countOfOptions,
                firstNumber,
                secondNumber
            )
            SUB -> generateDifferenceAnswers(
                maxExpressionNumber,
                countOfOptions,
                firstNumber,
                secondNumber
            )
            MULT -> generateProductAnswers(
                maxExpressionNumber,
                countOfOptions,
                firstNumber,
                secondNumber
            )
            DIV -> generateQuotientAnswers(
                maxExpressionNumber,
                countOfOptions,
                firstNumber,
                secondNumber
            )
        }

        return Question(firstNumber, secondNumber, answers)
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