package com.example.eracards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.content.Intent
import android.widget.ProgressBar
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity() {

    // Define a TAG for logging
    companion object {
        private const val TAG = "QuestionActivity" // Tag for logging
    }

    // UI Elements
    private lateinit var questionProgressBar: ProgressBar
    private lateinit var progressTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var feedbackTextView: TextView
    private lateinit var nextButton: Button

    //First parallel array: Contains the question strings
    private val questions = arrayOf(
        "Nelson Mandela became president of South Africa in 1994.",
        "The Great Wall of China is visible from the Moon with the naked eye.",
        "The French Revolution began in 1789.",
        "Cleopatra VII was of Greek descent, not Egyptian.",
        "World War I ended in 1917."
    )

    //Second parallel array: Contains the boolean answers corresponding to each question
    private val answers = booleanArrayOf(true, false, true, true, false)

    //Quiz State
    private var currentQuestionIndex = 0
    private var score = 0
    private var answerGiven = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        Log.d(TAG, "onCreate: Activity created.")

        // Initialize UI Elements
        questionProgressBar = findViewById(R.id.questionProgressBar)
        progressTextView = findViewById(R.id.progressTextView)
        questionTextView = findViewById(R.id.questionTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        feedbackTextView = findViewById(R.id.feedbackTextView)
        nextButton = findViewById(R.id.nextButton)
        Log.d(TAG, "UI elements initialized.")


        questionProgressBar.max = questions.size // Define o valor máximo da barra

        // Load the first question UI
        loadQuestion()

        // Set Button Click Listeners
        trueButton.setOnClickListener {
            Log.i(TAG, "True button clicked.")
            checkAnswer(true) }
        falseButton.setOnClickListener {
            Log.i(TAG, "False button clicked.")
            checkAnswer(false) }
        nextButton.setOnClickListener {
            Log.i(TAG, "Next button clicked.")
            moveToNextQuestion() }
    }

    // Function to update UI based on current QuizLogic state
    private fun loadQuestion() {
        if (currentQuestionIndex < questions.size) {
            val questionNumber = currentQuestionIndex + 1

            // Update Progress Bar and Text
            questionProgressBar.progress = questionNumber // Define o progresso atual
            progressTextView.text = "$questionNumber/${questions.size}" // Atualiza o texto "X/Y"

            // Display the question
            questionTextView.text = questions[currentQuestionIndex]

            // Reset for the new question
            feedbackTextView.text = ""
            trueButton.isEnabled = true
            falseButton.isEnabled = true
            nextButton.isEnabled = false
            answerGiven = false
        } else {
            Log.i(TAG, "Quiz finished. Navigating to Score screen.")
            goToScoreScreen()
        }
    }

    // Function to check the user's answer
    private fun checkAnswer(userAnswer: Boolean) {
        if (answerGiven) return

        answerGiven = true
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        nextButton.isEnabled = true

        val correctAnswer = answers[currentQuestionIndex]

        // Update Feedback UI
        if (userAnswer == correctAnswer) {
            score++
            feedbackTextView.text = "Correct!"
            feedbackTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        } else {
            feedbackTextView.text = "Incorrect. The answer was $correctAnswer."
            feedbackTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }
    }

    // Function to move to the next question
    private fun moveToNextQuestion() {
        currentQuestionIndex++
        loadQuestion()
    }

    // Function to navigate to the Score Activity
    private fun goToScoreScreen() {
        Log.d(TAG, "goToScoreScreen called.")
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("EXTRA_SCORE", score)
        intent.putExtra("EXTRA_TOTAL_QUESTIONS", questions.size)
        intent.putExtra("EXTRA_QUESTIONS", questions)
        intent.putExtra("EXTRA_ANSWERS", answers)
        startActivity(intent)
        finish()
    }
}