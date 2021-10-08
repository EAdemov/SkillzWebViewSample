package com.skillzwebviewsample

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.skillz.Skillz
import com.skillz.SkillzActivity
import android.webkit.WebViewClient
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProvider


/**
 * Created by enisademov on 8.10.21.
 */

class GameActivity : SkillzActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        webView = WebView(this)
        webView.webViewClient = WebViewClient()
        webView.isVerticalScrollBarEnabled = false
        val gameLayout = findViewById(R.id.rlGame) as RelativeLayout
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        webView.layoutParams = params
        gameLayout.addView(webView, 0)
        setWebViewSettings()

        webView.loadUrl("https://docs.skillz.com/docs/installing-skillz-android")

        val remoteConfigurations = getRemoteConfigurations()
        Log.d("GameActivity", "Remote Configuration $remoteConfigurations")
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

    private fun getRemoteConfigurations(): MutableMap<String, String> {
        return Skillz.getMatchRules()
    }
}