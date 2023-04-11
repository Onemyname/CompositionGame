package com.konovalov.compositiongame.domain.entity

class GameSettings(
    val maxSumValue: Int,
    val minCountRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
)