package com.example.eracards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    // Define a TAG for logging
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Link to the layout file
        Log.d(TAG, "onCreate: Activity created.")

        // Find the start button by its ID
        val startButton: Button = findViewById(R.id.startButton)

        // Set an action to perform when the start button is clicked
        startButton.setOnClickListener {
            Log.i(TAG, "Start button clicked.")
            // Create an Intent to start the QuestionActivity
            val intent = Intent(this, QuestionActivity::class.java)
            Log.d(TAG, "Starting QuestionActivity.")
            startActivity(intent) // Start the new activity
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity started.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity stopped.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity destroyed.")
    }
}