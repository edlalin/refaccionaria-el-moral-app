package com.refaccionariaelmoral.app

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var webView: WebView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        webView = findViewById(R.id.webview)
        
        // Configure WebView settings
        configureWebView()
        
        // Set custom WebView client
        webView.webViewClient = RefaccionariaWebViewClient(this)
        
        // Load the website
        webView.loadUrl("https://refaccionariaelmoral.sicarx.shop/")
    }
    
    private fun configureWebView() {
        val webSettings: WebSettings = webView.settings.apply {
            // Enable JavaScript for full website functionality
            javaScriptEnabled = true
            
            // Enable DOM storage for local storage/session storage
            domStorageEnabled = true
            
            // Enable database storage
            databaseEnabled = true
            
            // Allow mixed content (HTTP and HTTPS)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            
            // Cache settings for offline support
            cacheMode = WebSettings.LOAD_DEFAULT
            
            // Enable zoom controls
            builtInZoomControls = true
            displayZoomControls = false
            
            // User agent (optional: customize if needed)
            userAgentString = userAgentString + " RefaccionariaElMoralApp/1.0"
            
            // File access
            allowFileAccess = true
            allowContentAccess = true
        }
    }
    
    // Override back button to navigate within WebView history
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack()
                return true
            } else {
                Toast.makeText(this, "No more pages to go back", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }
}
