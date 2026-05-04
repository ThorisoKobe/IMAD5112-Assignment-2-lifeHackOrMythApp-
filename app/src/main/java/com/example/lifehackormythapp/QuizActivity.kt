package com.example.lifehackormythapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var nextBtn: Button

    private var currentIndex = 0
    private var score = 0

    private val questions = arrayOf(
        "Putting phone in rice fixes water damage",
        "Drinking water improves focus",
        "Cracking knuckles causes arthritis",
        "Keyboard shortcuts save time",
        "Charging overnight destroys battery"
    )

    private val answers = arrayOf(false, true, false, true, false)

    private val explanations = arrayOf(
        "Rice does not properly remove moisture.",
        "Hydration improves brain performance.",
        "No scientific proof.",
        "Improves efficiency and speed.",
        "Modern phones prevent overcharging."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)
        nextBtn = findViewById(R.id.nextBtn)

        val hackBtn = findViewById<Button>(R.id.hackBtn)
        val mythBtn = findViewById<Button>(R.id.mythBtn)

        loadQuestion()
        nextBtn.isEnabled = false

        hackBtn.setOnClickListener { checkAnswer(true) }
        mythBtn.setOnClickListener { checkAnswer(false) }

        nextBtn.setOnClickListener {
            currentIndex++

            if (currentIndex < questions.size) {
                loadQuestion()
                feedbackText.text = ""
                nextBtn.isEnabled = false
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        questionText.text = questions[currentIndex]
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentIndex]

        Log.d("QuizApp", "Q: ${questions[currentIndex]}")
        Log.d("QuizApp", "User: $userAnswer")

        if (userAnswer == correct) {
            score++
            feedbackText.text = "Correct! ${explanations[currentIndex]}"
        } else {
            feedbackText.text = "Wrong! ${explanations[currentIndex]}"
        }

        nextBtn.isEnabled = true
    }
}