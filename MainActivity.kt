package com.nitinsinghviril.reelsummary

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var summaryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        summaryTextView = findViewById(R.id.summaryTextView)

        if (intent?.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val reelUrl = intent.getStringExtra(Intent.EXTRA_TEXT)
            reelUrl?.let { summarizeReel(it) }
        }
    }

    private fun summarizeReel(url: String) {
        summaryTextView.text = "Gemini is watching the reel..."
        
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.GEMINI_KEY
        )

        MainScope().launch {
            try {
                val response = generativeModel.generateContent("Summarize this Instagram reel link: $url")
                summaryTextView.text = response.text
            } catch (e: Exception) {
                summaryTextView.text = "Error: ${e.message}"
            }
        }
    }
}
