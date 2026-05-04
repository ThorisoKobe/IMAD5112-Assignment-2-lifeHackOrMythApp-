package com.example.lifehackormythapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val scoreText = findViewById<TextView>(R.id.scoreText)
        val restartBtn = findViewById<Button>(R.id.restartBtn)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        val message: String
        if (score >= 4) {
            message = "Master Hacker!"
        } else {
            message = "Keep Practising!"
        }

        scoreText.text = "Score: $score / $total\n$message"

        // 🔥 Restart button logic
        restartBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}