package com.learning.mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.learning.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var diceA = 0
    var diceB = 0
    private var correctOption = 0.0
    private val wrongOptionList = mutableListOf<Double>()
    private lateinit var correctOptionPlace: TextView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            setPrimaryViewsVisibility()
        }

        binding.btnRollDice.setOnClickListener {
            setDicesText()
            setSecondaryViewsVisibility()
            setOptionsText()
            makeOptionsClickable()
        }

        binding.tvOption1.setOnClickListener {
            setBackgroundColorBasedOnUserAnswer(it)
            makeOptionsNonClickable()
        }

        binding.tvOption2.setOnClickListener {
            setBackgroundColorBasedOnUserAnswer(it)
            makeOptionsNonClickable()
        }

        binding.tvOption3.setOnClickListener {
            setBackgroundColorBasedOnUserAnswer(it)
            makeOptionsNonClickable()
        }

        binding.tvOption4.setOnClickListener {
            setBackgroundColorBasedOnUserAnswer(it)
            makeOptionsNonClickable()
        }
    }

    private fun setBackgroundColorBasedOnUserAnswer(tvOption: View) {
        if (tvOption == correctOptionPlace) {
            tvOption.setBackgroundColor(resources.getColor(R.color.green))
        } else {
            tvOption.setBackgroundColor(resources.getColor(R.color.red))
        }
    }

    private fun makeOptionsClickable() {
        binding.tvOption1.isClickable = true
        binding.tvOption2.isClickable = true
        binding.tvOption3.isClickable = true
        binding.tvOption4.isClickable = true
    }

    private fun makeOptionsNonClickable() {
        binding.tvOption1.isClickable = false
        binding.tvOption2.isClickable = false
        binding.tvOption3.isClickable = false
        binding.tvOption4.isClickable = false
    }

    private fun produceOptions() {
        correctOption = String.format("%.1f", diceA.toDouble() / diceB.toDouble()).toDouble()
        val smallNumberList = mutableListOf(0.1, 0.3, 0.5)
        for (number in smallNumberList) {
            wrongOptionList.add(String.format("%.1f", correctOption + number).toDouble())
        }
    }

    private fun setOptionsText() {
        produceOptions()
        val tvOptionList = mutableListOf(
            binding.tvOption1,
            binding.tvOption2,
            binding.tvOption3,
            binding.tvOption4
        )
        correctOptionPlace = tvOptionList.random()
        correctOptionPlace.text = correctOption.toString()
        tvOptionList.remove(correctOptionPlace)

        for ((i, tvOption) in tvOptionList.withIndex()) {
            tvOption.text = wrongOptionList[i].toString()
        }
    }

    private fun setSecondaryViewsVisibility() {
        binding.btnRollDice.visibility = View.GONE
        binding.tvDiceA.visibility = View.VISIBLE
        binding.tvDiceB.visibility = View.VISIBLE
        binding.tvMathOperator.visibility = View.VISIBLE
        binding.tvQuestionMark.visibility = View.VISIBLE
        binding.tvOption1.visibility = View.VISIBLE
        binding.tvOption2.visibility = View.VISIBLE
        binding.tvOption3.visibility = View.VISIBLE
        binding.tvOption4.visibility = View.VISIBLE
    }

    private fun setDicesText() {
        binding.tvDiceA.text = pickRandomA().toString()
        binding.tvDiceB.text = pickRandomB().toString()
    }

    private fun setPrimaryViewsVisibility() {
        binding.btnStart.visibility = View.GONE
        binding.btnRollDice.visibility = View.VISIBLE
    }

    private fun pickRandomA(): Int {
        diceA = (1..100).random()
        return diceA
    }

    private fun pickRandomB(): Int {
        diceB = (1..10).random()
        return diceB
    }
}