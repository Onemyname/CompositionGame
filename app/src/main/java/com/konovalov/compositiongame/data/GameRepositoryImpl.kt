package com.konovalov.compositiongame.data

import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.repository.GameRepository
import java.lang.Math.max
import java.lang.StrictMath.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val answers = generateListOfAnswers(maxSumValue, countOfOptions, sum, visibleNumber)

        return Question(sum, visibleNumber, answers)
    }

        override fun getGameSettings(difficultyLevel: DifficultyLevel, mathMode: MathMode): GameSettings {

        return when(difficultyLevel){
            DifficultyLevel.TEST-> GameSettings(
               10,
               3,
               50,
               8
            )
            DifficultyLevel.EASY-> GameSettings(
                10,
                10,
                70,
                60
            )
            DifficultyLevel.NORMAL-> GameSettings(
                20,
                20,
                80,
                40
            )
            DifficultyLevel.HARD->GameSettings(
                30,
                30,
                90,
                40
            )
        }
    }

    private fun generateListOfAnswers(
        maxSumValue: Int,
        countOfOptions: Int,
        sum: Int,
        visibleNumber: Int
    ): List<Int> {
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, GameRepositoryImpl.MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return options.toList()
    }
}