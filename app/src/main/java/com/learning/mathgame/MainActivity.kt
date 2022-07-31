package com.learning.mathgame
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.learning.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            setPrimaryViewsState()
        }
    }

    private fun setPrimaryViewsState() {
        binding.btnStart.visibility = View.GONE

        binding.tvDiceA.visibility = View.VISIBLE
        binding.tvDiceB.visibility = View.VISIBLE
        binding.tvMathOperator.visibility = View.VISIBLE
        binding.tvQuestionMark.visibility = View.VISIBLE
    }
}