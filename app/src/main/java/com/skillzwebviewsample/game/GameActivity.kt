package com.skillzwebviewsample.game

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import com.skillz.Skillz
import com.skillz.SkillzActivity
import android.webkit.WebViewClient
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.skillz.SkillzScoreCallback
import com.skillzwebviewsample.R
import com.skillzwebviewsample.base.ViewModelFactory
import java.math.BigDecimal


/**
 * Created by enisademov on 8.10.21.
 */

class GameActivity : SkillzActivity() {
    private lateinit var webView: WebView
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[GameViewModel::class.java]

        webView = findViewById(R.id.wvGame) as WebView
        webView.webViewClient = WebViewClient()
        webView.isVerticalScrollBarEnabled = false
        setWebViewSettings()

        webView.loadUrl("https://docs.skillz.com/docs/installing-skillz-android")

        viewModel.getAppInfo().observe(this, { result ->
            Log.d("GameActivity", "Remote Configuration $result")
        })

        Toast.makeText(this, "Game will finish in 5 sec", Toast.LENGTH_LONG).show()

        Handler().postDelayed({
            if (Skillz.isMatchInProgress()) {
                val matchInfo = Skillz.getMatchInfo(this)
                matchInfo?.let {
                    Skillz.submitScore(
                        this,
                        BigDecimal(1000),
                        it.id,
                        object : SkillzScoreCallback {
                            override fun failure(error: Exception) {}

                            override fun success() {
                                Skillz.displayTournamentResultsWithScore(
                                    this@GameActivity,
                                    BigDecimal(1000),
                                    it.id
                                )
                            }
                        })
                }
            }
        }, 5000)
    }

    private fun setWebViewSettings() {
        webView.settings.apply {
            javaScriptEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            domStorageEnabled = true
        }
        WebView.setWebContentsDebuggingEnabled(true)
    }
}