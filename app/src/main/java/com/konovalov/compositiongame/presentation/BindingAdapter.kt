package com.konovalov.compositiongame.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.konovalov.compositiongame.R
import com.konovalov.compositiongame.domain.entity.GameResult
import com.konovalov.compositiongame.domain.entity.MathMode

interface OnOptionClickListener {

    fun onOptionClick(option: Int)
}


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

@BindingAdapter("resultImage")
fun bindResultImage(imageView: ImageView, winner: Boolean){
    imageView.setImageResource(getResultImageId(winner))
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean){
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean){
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("arithmeticSign")
fun bindArithmeticSign(textView: TextView, mathMode: MathMode){
    val sign : String = getSign(textView.context, mathMode)
    textView.text = sign
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(button: Button, clickListener: OnOptionClickListener){
    button.setOnClickListener{
        clickListener.onOptionClick(button.text.toString().toInt())
    }
}
private fun getSign(context: Context,mathMode: MathMode): String {
    return when (mathMode) {
        MathMode.ADDITION -> context.resources.getString(R.string.addition)
        MathMode.SUBTRACTION -> context.resources.getString(R.string.subtraction)
        MathMode.MULTIPLICATION -> context.resources.getString(R.string.multiplication)
        MathMode.DIVISION -> context.resources.getString(R.string.division)
    }
}

private fun getColorByState(context: Context, goodState: Boolean) : Int{
    val colorResId = if (goodState) R.color.forest_green else android.R.color.holo_red_light

    return ContextCompat.getColor(context, colorResId)
}

private fun getPercentage(gameResult: GameResult): Int = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        (countOfRightAnswers.toDouble() / countOfQuestions * 100).toInt()
    }
}

fun getResultImageId(winner: Boolean): Int {
        return if (winner) TODO() else TODO()
}
