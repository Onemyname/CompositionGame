package com.konovalov.compositiongame.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.domain.entity.GameResult


@BindingAdapter("requiredAnswer")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercentAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}
@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}
@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentage(gameResult)
    )
}
private fun getPercentage(gameResult: GameResult): Int = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        (countOfRightAnswers.toDouble() / countOfQuestions * 100).toInt()
    }
}

@BindingAdapter("resultImage")
fun bindResultImage(imageView: ImageView, winner: Boolean){
    imageView.setImageResource(getResultImageId(winner))
}

fun getResultImageId(winner: Boolean): Int {
        return if (winner) TODO() else TODO()
}
