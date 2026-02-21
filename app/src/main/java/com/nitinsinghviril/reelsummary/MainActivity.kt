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

        // Receive the share intent from Instagram
        if (intent?.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val reelUrl = intent.getStringExtra(Intent.EXTRA_TEXT)
            reelUrl?.let { summarizeWithAI(it) }
        }
    }

    private fun summarizeWithAI(url: String) {
        summaryTextView.text = "Contacting Gemini AI..."
        
        // We use the key from BuildConfig here
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.GEMINI_KEY
        )

        MainScope().launch {
            try {
                val response = generativeModel.generateContent("Analyze this Reel link and summarize it: $url")
                summaryTextView.text = response.text
            } catch (e: Exception) {
                summaryTextView.text = "Error: Check if API Key is correct in Secrets."
            }
        }
    }
}
