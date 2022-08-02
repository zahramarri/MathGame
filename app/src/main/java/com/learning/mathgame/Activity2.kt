package com.learning.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.mathgame.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        binding.btnNewGame.setOnClickListener {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initViews() {
        val totalScore = intent.getIntExtra(EXTRA_MESSAGE, 0)
        binding.tvTotalScore2.setText("SCORE: $totalScore")
    }
}