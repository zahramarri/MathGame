package com.learning.mathgame

import android.widget.TextView

class GameState(
    var diceA: Int = 0,
    var diceB: Int = 0,
    var correctOption: Double = 0.0,
    var correctOptionPlace: TextView,
    var totalScore: Int = 0,
    var gameRound: Int = 1,
    var diceARange: IntRange = (1..100),
    var diceBRange: IntRange = (1..10),
    var operator: String = "\u00F7",
    var wrongOptionList: MutableList<Double> = mutableListOf<Double>()
)
