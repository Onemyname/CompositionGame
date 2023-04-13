package com.konovalov.compositiongame.data.numbersGenerators

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

private const val MIN_VALUE = 2

fun generateDividend(maxExpressionNumber: Int): Int {

    return Random.nextInt(MIN_VALUE, maxExpressionNumber + 1)
}

fun generateDivisior(firstNumber: Int): Int {
    val possibleDivisiors: HashSet<Int> = HashSet()
    for (i in MIN_VALUE..firstNumber) {
        if (firstNumber % i == 0) {
            possibleDivisiors.add(i)
        }
    }

    return possibleDivisiors.random()
}

fun generateQuotientAnswers(
    maxExpressionNumber: Int,
    countOfOptions: Int,
    firstNumber: Int,
    secondNumber: Int
): List<Int> {
    val options = HashSet<Int>()
    val rightAnswer = firstNumber / secondNumber
    options.add(rightAnswer)
    val from = max(rightAnswer - countOfOptions, MIN_VALUE)
    val to = min(maxExpressionNumber, rightAnswer + countOfOptions)
    while (options.size < countOfOptions) {
        options.add(Random.nextInt(from, to))
    }
    return options.toList()
}
