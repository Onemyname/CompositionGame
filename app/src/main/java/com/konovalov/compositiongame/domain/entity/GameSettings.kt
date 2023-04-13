package com.konovalov.compositiongame.domain.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GameSettings(
    private val mathMode: MathMode,
    private val maxExpressionNumber: Int,
    private val minCountRightAnswers: Int,
    private val minPercentOfRightAnswers: Int,
    private val gameTimeInSeconds: Int
) : Parcelable