package com.example.eracards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ReviewActivity : AppCompatActivity() {

    // Define a TAG for logging
    companion object {
        private const val TAG = "ReviewActivity" // Tag for logging
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        Log.d(TAG, "onCreate: Activity created.")

        // --- Get UI Elements ---
        val reviewContentTextView: TextView = findViewById(R.id.reviewContentTextView)
        val backButton: Button = findViewById(R.id.backButton)
        Log.d(TAG, "UI elements initialized.")

        // --- Retrieve data from Intent ---
        val questions = intent.getStringArrayExtra("EXTRA_QUESTIONS") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("EXTRA_ANSWERS") ?: booleanArrayOf()

        // --- Build the review text ---
        val reviewText = StringBuilder()
        for (i in questions.indices) {
            reviewText.append("Q${i + 1}: ${questions[i]}\n")
            reviewText.append("Correct Answer: ${answers[i]}\n\n")
        }

        // --- Display the review content ---
        reviewContentTextView.text = reviewText.toString()

        // --- Set Button Click Listener ---
        backButton.setOnClickListener {
            finish() // Close this activity and return to ScoreActivity
        }
    }
}