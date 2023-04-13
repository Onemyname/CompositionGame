package com.konovalov.compositiongame.domain.repository

import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxExpressionNumber: Int,
        countOfOptions: Int,
        mathMode: MathMode
    ): Question

    fun getGameSettings(difficultyLevel: DifficultyLevel, mathMode: MathMode): GameSettings
}