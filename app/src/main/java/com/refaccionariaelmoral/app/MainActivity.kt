package com.refaccionariaelmoral.app

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        setupWebView()

        // Load the website
        webView.loadUrl("https://refaccionariaelmoral.sicarx.shop/")
    }

    private fun setupWebView() {
        webView.apply {
            // Enable JavaScript
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                useWideViewPort = true
                loadWithOverviewMode = true
                defaultTextEncodingName = "utf-8"
                loadsImagesAutomatically = true
                pluginState = WebSettings.PluginState.ON
            }

            // Set WebViewClient to handle URL loading
            webViewClient = RefaccionariaWebViewClient()

            // Set WebChromeClient to handle JavaScript dialogs
            webChromeClient = WebChromeClient()
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
