package com.konovalov.compositiongame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GameSettings(
    val mathMode: MathMode,
    val maxNumber: Int,
    val minCountRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Parcelable