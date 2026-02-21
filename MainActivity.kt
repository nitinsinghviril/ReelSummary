package com.nitinsinghviril.reelsummary

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var summaryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        summaryTextView = findViewById(R.id.summaryTextView)

        // Check if the app was opened via the "Share" button
        if (intent?.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            handleSharedReel(intent)
        }
    }

    private fun handleSharedReel(intent: Intent) {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (sharedText != null) {
            // This is the Instagram link! 
            summaryTextView.text = "Fetching summary for: $sharedText..."
            
            // NEXT STEP: Call your Gemini API function here
            // summarizeWithGemini(sharedText)
        }
    }
}
