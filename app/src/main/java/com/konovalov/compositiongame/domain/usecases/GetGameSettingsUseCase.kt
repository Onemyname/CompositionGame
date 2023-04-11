package com.konovalov.compositiongame.domain.usecases

import com.konovalov.compositiongame.domain.entity.GameSettings
import com.konovalov.compositiongame.domain.entity.Level
import com.konovalov.compositiongame.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level) : GameSettings{
        return repository.getGameSettings(level)
    }
}