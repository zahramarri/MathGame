package com.learning.mathgame
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.learning.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var diceA = 0
    var diceB = 0
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
            setOptions()
        }
    }

    private fun setOptions() {
        val optionSet = mutableListOf(binding.tvOption1, binding.tvOption2, binding.tvOption3, binding.tvOption4)
        val correctAnswerPlace = optionSet.random()
        val correctOption = String.format("%.1f", diceA.toDouble()/diceB.toDouble()).toDouble()
        val littleNumber = mutableListOf(0.1, 0.3, 0.5, 0.7)
        optionSet.remove(correctAnswerPlace)
        correctAnswerPlace.text = correctOption.toString()

        for ((i, option) in optionSet.withIndex()) {
            option.text = (correctOption + littleNumber[i]).toString()
        }
    }

    private fun setSecondaryViewsVisibility() {
        binding.btnRollDice.visibility = View.GONE

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

        binding.tvDiceA.visibility = View.VISIBLE
        binding.tvDiceB.visibility = View.VISIBLE
        binding.tvMathOperator.visibility = View.VISIBLE
        binding.tvQuestionMark.visibility = View.VISIBLE
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