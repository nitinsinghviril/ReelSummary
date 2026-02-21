override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    // Check if the app was opened via a "Share" action
    if (intent?.action == Intent.ACTION_SEND && intent.type == "text/plain") {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        sharedText?.let { reelUrl ->
            // Now we send this URL to Gemini!
            generateSummary(reelUrl)
        }
    }
}
