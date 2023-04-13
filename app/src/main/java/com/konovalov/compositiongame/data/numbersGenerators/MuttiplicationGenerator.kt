package com.konovalov.compositiongame.data.numbersGenerators

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

private const val MIN_VALUE = 2

fun generateMultiplicanda(maxExpressionNumber: Int): Int {

    return Random.nextInt(MIN_VALUE, maxExpressionNumber/2)
}

fun generateMultiplier(maxExpressionNumber: Int, firstNumber: Int): Int {
    val possibleMultipliers = HashSet<Int>()
    for (i in MIN_VALUE..firstNumber){
        if(firstNumber*i <= maxExpressionNumber){
            possibleMultipliers.add(i)
        }
    }

    return possibleMultipliers.random()
}

fun generateProductAnswers(
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