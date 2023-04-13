package com.konovalov.compositiongame.data.numbersGenerators

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

private const val MIN_VALUE = 1

fun generateSubtrahend( firstNumber: Int): Int {

    return Random.nextInt(MIN_VALUE,firstNumber)
}

fun generateMinuend(maxExpressionNumber: Int): Int {

    return Random.nextInt(MIN_VALUE+1,maxExpressionNumber+1)
}

fun generateDifferenceAnswers(
    maxExpressionNumber: Int,
    countOfOptions: Int,
    firstNumber: Int,
    secondNumber: Int
): List<Int> {
    val options = HashSet<Int>()
    val rightAnswer = firstNumber - secondNumber
    options.add(rightAnswer)

    val from = max(rightAnswer - countOfOptions, MIN_VALUE)
    val to = min(maxExpressionNumber, rightAnswer + countOfOptions)

    while (options.size < countOfOptions) {
        options.add(Random.nextInt(from, to))
    }
    return options.toList()
}