package com.refaccionariaelmoral.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.webkit.WebResourceRequest
import android.webkit.WebResourceError
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import android.os.Build

class RefaccionariaWebViewClient(private val context: Context) : WebViewClient() {
    
    /**
     * Called when a page starts loading
     */
    override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
        super.onPageStarted(view, url, favicon)
        // You can show a loading indicator here if desired
    }
    
    /**
     * Called when a page finishes loading
     */
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        // You can hide the loading indicator here
    }
    
    /**
     * Handle resource loading errors
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest,
        error: WebResourceError
    ) {
        super.onReceivedError(view, request, error)
        
        // Check if we have internet connectivity
        if (!isNetworkAvailable()) {
            // Load offline error page
            view.loadUrl("about:blank")
            val htmlData = """
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
                        .container { padding: 20px; }
                        h1 { color: #d32f2f; }
                        p { color: #666; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>📡 No Internet Connection</h1>
                        <p>Please check your internet connection and try again.</p>
                    </div>
                </body>
                </html>
            """.trimIndent()
            view.loadData(htmlData, "text/html; charset=utf-8", "utf-8")
        } else {
            // Show server error
            val htmlData = """
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
                        .container { padding: 20px; }
                        h1 { color: #ff9800; }
                        p { color: #666; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>⚠️ Loading Error</h1>
                        <p>Unable to load the page. Please try again later.</p>
                        <p>Error Code: $${error.errorCode}</p>
                    </div>
                </body>
                </html>
            """.trimIndent()
            view.loadData(htmlData, "text/html; charset=utf-8", "utf-8")
        }
    }
    
    /**
     * Check if the device has internet connectivity
     */
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
    
    /**
     * Prevent external links from opening in system browser (optional)
     * Uncomment if you want all links to open within the WebView
     */
    override fun shouldOverrideUrlLoading(
        view: WebView,
        request: WebResourceRequest
    ): Boolean {
        // Allow all URLs to load within the WebView
        return false
    }
}
