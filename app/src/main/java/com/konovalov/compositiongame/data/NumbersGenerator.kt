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
    fun generateFirstNumber(maxNumber: Int, mathMode: MathMode): Int {
        return when (mathMode) {
            ADDITION -> Random.nextInt(MIN_VALUE, maxNumber)
            SUBTRACTION -> Random.nextInt(MIN_VALUE + 1, maxNumber + 1)
            MULTIPLICATION -> Random.nextInt(SECOND_VALUE, maxNumber / 2)
            DIVISION -> dividend(maxNumber)
        }
    }

    fun generateSecondNumber(firstNumber: Int, maxNumber: Int, mathMode: MathMode): Int {
        return when (mathMode) {
            ADDITION -> Random.nextInt(MIN_VALUE, maxNumber - firstNumber + 1)
            SUBTRACTION -> Random.nextInt(MIN_VALUE, firstNumber)
            MULTIPLICATION -> multiplier(maxNumber, firstNumber)
            DIVISION -> divisor(firstNumber)
        }
    }


    private fun dividend(maxNumber: Int): Int {
        val list = mutableListOf<Int>()
        for (i in SECOND_VALUE until maxNumber + 1) {
            if (i % 2 == 0 || i % 3 == 0 || i % 5 == 0) list.add(i)
        }

        return list.random()
    }

    private fun divisor(firstNumber: Int): Int {
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

    private fun multiplier(maxNumber: Int, firstNumber: Int): Int {
        val possibleMultipliers = HashSet<Int>()
        for (i in SECOND_VALUE..firstNumber) {
            if (firstNumber * i <= maxNumber) {
                possibleMultipliers.add(i)
            }
        }
        return possibleMultipliers.random()
    }

    //answer options including only one correct
    fun getAnswerAndOptions(
        maxNumber: Int,
        countOptions: Int,
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
        val from = max(rightAnswer - countOptions, MIN_VALUE)
        val to = min(maxNumber, rightAnswer + countOptions)
        while (options.size < countOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Pair(rightAnswer, options.toList())
    }
}