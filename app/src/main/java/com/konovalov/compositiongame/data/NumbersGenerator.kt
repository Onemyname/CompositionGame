package com.konovalov.compositiongame.data

import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.MathMode.*
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object NumbersGenerator {
    private const val MIN_VALUE = 1
    private const val SECOND_VALUE = 2
    private val listOfDividend = mutableListOf<Int>()

    //ADDITION:

    fun firstSummand(maxExpressionNumber: Int): Int {

        return Random.nextInt(MIN_VALUE, maxExpressionNumber)
    }

    fun secondSummand(maxExpressionNumber: Int, firstNumber: Int): Int {

        return Random.nextInt(MIN_VALUE, maxExpressionNumber - firstNumber + 1)
    }

    //SUBTRACTION

    fun subtrahend(firstNumber: Int): Int {

        return Random.nextInt(MIN_VALUE, firstNumber)
    }

    fun minuend(maxExpressionNumber: Int): Int {

        return Random.nextInt(MIN_VALUE + 1, maxExpressionNumber + 1)
    }

    //MULTIPLICATION

    fun multiplicand(maxExpressionNumber: Int): Int {

        return Random.nextInt(SECOND_VALUE, maxExpressionNumber / 2)
    }

    fun multiplier(maxExpressionNumber: Int, firstNumber: Int): Int {
        val possibleMultipliers = HashSet<Int>()
        for (i in SECOND_VALUE..firstNumber) {
            if (firstNumber * i <= maxExpressionNumber) {
                possibleMultipliers.add(i)
            }
        }

        return possibleMultipliers.random()
    }

    //DIVISION

    fun dividend(maxExpressionNumber: Int): Int {
        fillListOfDividend(maxExpressionNumber)

        return listOfDividend.random()

    }

    private fun fillListOfDividend(maxExpressionNumber: Int) {

        for (i in SECOND_VALUE until maxExpressionNumber + 1) {
            if (
                i % 2 == 0
                || i % 3 == 0
                || i % 5 == 0
            )
                listOfDividend.add(i)
        }
    }

    fun divisor(firstNumber: Int): Int {
        val possibleDivisors: HashSet<Int> = HashSet()
        for (i in SECOND_VALUE until firstNumber) {
            if (firstNumber % i == 0) {
                possibleDivisors.add(i)
            }
        }
        if (possibleDivisors.size != 1 && possibleDivisors.contains(firstNumber)) {
            possibleDivisors.remove(firstNumber)
        }
        if(possibleDivisors.size == 0){
            possibleDivisors.add(firstNumber)
        }
        return possibleDivisors.random()
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