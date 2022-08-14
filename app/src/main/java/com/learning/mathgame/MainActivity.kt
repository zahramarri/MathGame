package com.learning.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import com.learning.mathgame.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "total score"

class MainActivity : AppCompatActivity() {
    private lateinit var gameState: GameState
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        countDownTimer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.tvCountDownTimer.text = "Seconds Remaining: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                binding.tvCountDownTimer.text = "Time Out!"
                makeOptionsNonClickable()
                resetBtnRollDice(gameState.gameRound)
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState != null) {

        }

        binding.btnStart.setOnClickListener {
            initGameStateObject()
            if (controlDicesRange()) {
                setPrimaryViewsVisibility()
            }
        }

        binding.btnRollDice.setOnClickListener {
            setDicesText()
            setSecondaryViewsVisibility()
            setOptionsText()
            makeOptionsClickable()
            resetBackgroundColor()
            gameState.gameRound++
            countDownTimer.start()
        }

        binding.tvOption1.setOnClickListener {
            checkUserAnswer(it)
            makeOptionsNonClickable()
            resetBtnRollDice(gameState.gameRound)
            countDownTimer.cancel()
        }

        binding.tvOption2.setOnClickListener {
            checkUserAnswer(it)
            makeOptionsNonClickable()
            resetBtnRollDice(gameState.gameRound)
            countDownTimer.cancel()
        }

        binding.tvOption3.setOnClickListener {
            checkUserAnswer(it)
            makeOptionsNonClickable()
            resetBtnRollDice(gameState.gameRound)
            countDownTimer.cancel()
        }

        binding.tvOption4.setOnClickListener {
            checkUserAnswer(it)
            makeOptionsNonClickable()
            resetBtnRollDice(gameState.gameRound)
            countDownTimer.cancel()
        }

        binding.tvAdditionOperator.setOnClickListener {
            setOperator(binding.tvAdditionOperator)
        }

        binding.tvSubtractionOperator.setOnClickListener {
            setOperator(binding.tvSubtractionOperator)
        }

        binding.tvMultiplicationOperator.setOnClickListener {
            setOperator(binding.tvMultiplicationOperator)
        }

        binding.tvDivisionOperator.setOnClickListener {
            setOperator(binding.tvDivisionOperator)
        }
    }

    private fun initGameStateObject() {
        gameState = GameState(
            0,
            0,
            0.0,
            binding.tvOption1,
            0,
            1,
            (1..100),
            (1..10),
            "\u00F7",
            mutableListOf<Double>()
        )
    }

    private fun controlDicesRange(): Boolean {
        var isOk1 = false
        var isOk2 = false
        if (binding.edtDiceARangeStart.text.toString().isNotBlank()) {
            if ((binding.edtDiceARangeStart.text.toString().length == 1) ||
                binding.edtDiceARangeStart.text.toString().length > 1 && binding.edtDiceARangeStart.text.toString()
                    .first() != '0'
            ) {
                if ((binding.edtDiceARangeEnd.text.toString().length == 1) ||
                    binding.edtDiceARangeEnd.text.toString().length > 1 && binding.edtDiceARangeEnd.text.toString()
                        .first() != '0'
                ) {
                    if (binding.edtDiceARangeStart.text.toString()
                            .toInt() < binding.edtDiceARangeEnd.text.toString().toInt()
                    ) {
                        setDiceARange()
                        isOk1 = true
                    } else {
                        binding.edtDiceARangeEnd.error = "Invalid Range"
                    }
                } else {
                    binding.edtDiceARangeEnd.error = "Invalid Number"
                }
            } else {
                binding.edtDiceARangeStart.error = "Invalid Number"
            }
        } else {
            binding.edtDiceARangeStart.error = "Invalid Input"
        }

        if (binding.edtDiceBRangeStart.text.toString().isNotBlank()) {
            if ((binding.edtDiceBRangeStart.text.toString().length == 1) ||
                binding.edtDiceBRangeStart.text.toString().length > 1 && binding.edtDiceBRangeStart.text.toString()
                    .first() != '0'
            ) {
                if ((binding.edtDiceBRangeEnd.text.toString().length == 1) ||
                    binding.edtDiceBRangeEnd.text.toString().length > 1 && binding.edtDiceBRangeEnd.text.toString()
                        .first() != '0'
                ) {
                    if (binding.edtDiceBRangeStart.text.toString()
                            .toInt() < binding.edtDiceBRangeEnd.text.toString().toInt()
                    ) {
                        setDiceBRange()
                        isOk2 = true
                    } else {
                        binding.edtDiceBRangeEnd.error = "Invalid Range"
                    }
                } else {
                    binding.edtDiceBRangeEnd.error = "Invalid Number"
                }
            } else {
                binding.edtDiceBRangeStart.error = "Invalid Number"
            }
        } else {
            binding.edtDiceBRangeStart.error = "Invalid Input"
        }

        return (isOk1 && isOk2)
    }

    private fun setDiceBRange() {
        gameState.diceBRange = (binding.edtDiceBRangeStart.text.toString()
            .toInt()..binding.edtDiceBRangeEnd.text.toString().toInt())
    }

    private fun setDiceARange() {
        gameState.diceARange = (binding.edtDiceARangeStart.text.toString()
            .toInt()..binding.edtDiceARangeEnd.text.toString().toInt())
    }

    private fun setOperator(textView: TextView) {
        binding.tvMathOperator.text = textView.text
        gameState.operator = textView.text.toString()
    }

    private fun startActivity2() {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra(EXTRA_MESSAGE, gameState.totalScore)
        startActivity(intent)
    }

    private fun resetBackgroundColor() {
        binding.tvOption1.setBackgroundColor(0)
        binding.tvOption2.setBackgroundColor(0)
        binding.tvOption3.setBackgroundColor(0)
        binding.tvOption4.setBackgroundColor(0)
    }

    private fun resetBtnRollDice(gameRound: Int) {
        if (gameRound < 6) {
            binding.btnRollDice.visibility = View.VISIBLE
            binding.btnRollDice.text = getString(R.string.btnRollDice2)
        } else {
            startActivity2()
        }
    }

    private fun checkUserAnswer(tvOption: View) {
        if (tvOption == gameState.correctOptionPlace) {
            increaseTotalScore()
            setBackgroundColorBasedOnAnswer(true, tvOption)
        } else {
            decreaseTotalScore()
            setBackgroundColorBasedOnAnswer(false, tvOption)
        }
    }

    private fun setBackgroundColorBasedOnAnswer(answer: Boolean, tvOption: View) {
        if (answer) {
            tvOption.setBackgroundColor(resources.getColor(R.color.green))
        } else {
            tvOption.setBackgroundColor(resources.getColor(R.color.red))
        }
    }

    private fun decreaseTotalScore() {
        gameState.totalScore -= 2
        binding.tvTotalScore.text = gameState.totalScore.toString()
    }

    private fun increaseTotalScore() {
        gameState.totalScore += 5
        binding.tvTotalScore.text = gameState.totalScore.toString()
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
        gameState.correctOption = when (gameState.operator) {
            binding.tvDivisionOperator.text.toString() -> String.format(
                "%.1f",
                gameState.diceA.toDouble() / gameState.diceB.toDouble()
            ).toDouble()
            binding.tvMultiplicationOperator.text.toString() -> String.format(
                "%.1f",
                gameState.diceA.toDouble() * gameState.diceB.toDouble()
            ).toDouble()
            binding.tvAdditionOperator.text.toString() -> String.format(
                "%.1f",
                gameState.diceA.toDouble() + gameState.diceB.toDouble()
            ).toDouble()
            else -> String.format("%.1f", gameState.diceA.toDouble() - gameState.diceB.toDouble()).toDouble()
        }

        val smallNumberList = mutableListOf(0.1, 0.3, 0.5)
        gameState.wrongOptionList.clear()
        for (number in smallNumberList) {
            gameState.wrongOptionList.add(String.format("%.1f", gameState.correctOption + number).toDouble())
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
        gameState.correctOptionPlace = tvOptionList.random()
        gameState.correctOptionPlace.text = gameState.correctOption.toString()
        tvOptionList.remove(gameState.correctOptionPlace)

        for ((i, tvOption) in tvOptionList.withIndex()) {
            tvOption.text = gameState.wrongOptionList[i].toString()
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
        binding.tvSubtractionOperator.visibility = View.GONE
        binding.tvAdditionOperator.visibility = View.GONE
        binding.tvMultiplicationOperator.visibility = View.GONE
        binding.tvDivisionOperator.visibility = View.GONE
        binding.tvChooseMathOperator.visibility = View.GONE
        binding.tvDiceARange.visibility = View.GONE
        binding.tvDiceBRange.visibility = View.GONE
        binding.tvTo1.visibility = View.GONE
        binding.tvTo2.visibility = View.GONE
        binding.edtDiceBRangeStart.visibility = View.GONE
        binding.edtDiceBRangeEnd.visibility = View.GONE
        binding.edtDiceARangeStart.visibility = View.GONE
        binding.edtDiceARangeEnd.visibility = View.GONE
        binding.btnRollDice.visibility = View.VISIBLE
    }

    private fun pickRandomA(): Int {
        gameState.diceA = gameState.diceARange.random()
        return gameState.diceA
    }

    private fun pickRandomB(): Int {
        gameState.diceB = gameState.diceBRange.random()
        return gameState.diceB
    }

}