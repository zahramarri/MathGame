package com.learning.mathgame

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.mathgame.databinding.Activity2Binding
import kotlin.system.exitProcess

class Activity2 : AppCompatActivity() {
    var record = 0
    var recordTitle = "RECORD:"
    lateinit var sharedPref: SharedPreferences
    var totalScore = 0
    lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getPreferences(MODE_PRIVATE)

        initViewTotalScore()
        writeInSharedPref()
        initViewRecord()

        binding.btnNewGame.setOnClickListener {
            startMainActivity()
        }

        binding.btnExit.setOnClickListener {
            exitApp()
        }
    }

    private fun exitApp() {
        moveTaskToBack(true)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(1)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initViewTotalScore() {
        totalScore = intent.getIntExtra(EXTRA_MESSAGE, 0)
        binding.tvTotalScore2.text = "SCORE: $totalScore"
    }

    private fun initViewRecord() {
        record = readFromSharedPref()
        binding.tvRecord.text = "$recordTitle $record"
    }

    private fun writeInSharedPref() {
        val editor = sharedPref.edit()
        if (totalScore > readFromSharedPref()) {
            editor.putInt(sharedPrefKey, totalScore)
            editor.apply()
            recordTitle = "NEW RECORD:"
        }
    }

    private fun readFromSharedPref(): Int {
        return sharedPref.getInt(sharedPrefKey, 0)
    }

    companion object {
        const val sharedPrefKey = "RECORD"
    }
}