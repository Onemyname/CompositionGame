package com.konovalov.compositiongame.data

import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.MathMode.ADDITION
import com.konovalov.compositiongame.domain.entity.MathMode.DIVISION
import com.konovalov.compositiongame.domain.entity.MathMode.MULTIPLICATION
import com.konovalov.compositiongame.domain.entity.MathMode.SUBTRACTION
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object NumbersGenerator {
    private const val MIN_VALUE = 1
    private const val SECOND_VALUE = 2


    //ADDITION:
    fun generateFirstNumber(maxExpressionNumber: Int, mathMode: MathMode): Int {
        return when (mathMode) {
            ADDITION -> Random.nextInt(MIN_VALUE, maxExpressionNumber)
            SUBTRACTION -> Random.nextInt(MIN_VALUE + 1, maxExpressionNumber + 1)
            MULTIPLICATION -> Random.nextInt(SECOND_VALUE, maxExpressionNumber / 2)
            DIVISION -> possibleDividends(maxExpressionNumber).random()
        }
    }

    fun generateSecondNumber(firstNumber: Int, maxExpressionNumber: Int, mathMode: MathMode): Int {
        return when (mathMode) {
            ADDITION -> Random.nextInt(MIN_VALUE, maxExpressionNumber - firstNumber + 1)
            SUBTRACTION -> Random.nextInt(MIN_VALUE, firstNumber)
            MULTIPLICATION -> possibleMultipliers(maxExpressionNumber, firstNumber).random()
            DIVISION -> getDivisor(firstNumber)
        }
    }


    private fun possibleDividends(maxExpressionNumber: Int): List<Int> {
        val list = mutableListOf<Int>()
        for (i in SECOND_VALUE until maxExpressionNumber + 1) {
            if (i % 2 == 0 || i % 3 == 0 || i % 5 == 0) list.add(i)
        }

        return list
    }

    private fun getDivisor(firstNumber: Int): Int {
        val possibleDivisors: HashSet<Int> = HashSet()
        for (i in SECOND_VALUE until firstNumber) {
            if (firstNumber % i == 0) {
                possibleDivisors.add(i)
            }
        }
        if (possibleDivisors.size == 0) {
            return firstNumber
        }

        return possibleDivisors.random()
    }

    private fun possibleMultipliers(maxExpressionNumber: Int, firstNumber: Int): HashSet<Int> {
        val possibleMultipliers = HashSet<Int>()
        for (i in SECOND_VALUE..firstNumber) {
            if (firstNumber * i <= maxExpressionNumber) {
                possibleMultipliers.add(i)
            }
        }
        return possibleMultipliers
    }

    //answer options including only one correct
    fun getAnswerAndOptions(
        maxExpressionNumber: Int,
        countOfOptions: Int,
        firstNumber: Int,
        secondNumber: Int,
        mathMode: MathMode
    ): Pair<Int, List<Int>> {
        val options = HashSet<Int>()
        val rightAnswer = when (mathMode) {
            ADDITION -> firstNumber + secondNumber
            SUBTRACTION -> firstNumber - secondNumber
            MULTIPLICATION -> firstNumber * secondNumber
            DIVISION -> firstNumber / secondNumber
        }
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_VALUE)
        val to = min(maxExpressionNumber, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Pair(rightAnswer, options.toList())
    }
}