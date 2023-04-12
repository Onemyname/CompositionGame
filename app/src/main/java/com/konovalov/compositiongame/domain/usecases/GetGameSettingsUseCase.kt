package com.konovalov.compositiongame.domain.usecases

import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.DifficultyLevel
import com.konovalov.compositiongame.domain.entity.MathMode
import com.konovalov.compositiongame.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(difficultyLevel: DifficultyLevel, mathMode: MathMode) : GameSettings{
        return repository.getGameSettings(difficultyLevel, mathMode)
    }
}