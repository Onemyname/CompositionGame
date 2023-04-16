package com.konovalov.compositiongame.domain.entity

class Question(
    val firstNumber: Int,
    val secondNumber: Int,
    val rightAnswer: Int,
    val options: List<Int>,
)