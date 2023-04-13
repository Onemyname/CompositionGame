package com.konovalov.compositiongame.domain.usecases

import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.Question
import com.konovalov.compositiongame.domain.repository.GameRepository

class GenerateQuestionUeCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int, mathMode: MathMode): Question{
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS, mathMode)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}