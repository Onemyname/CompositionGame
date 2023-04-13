package com.konovalov.compositiongame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class DifficultyLevel : Parcelable {
    TEST, EASY, NORMAL, HARD

}