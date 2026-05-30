package com.refaccionariaelmoral.app

import android.webkit.WebView
import android.webkit.WebViewClient

class RefaccionariaWebViewClient : WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        // Page load finished
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            // Allow navigation within the same domain
            if (url.contains("refaccionariaelmoral.sicarx.shop") || 
                url.contains("sicarx.shop") ||
                url.startsWith("https://refaccionariaelmoral.sicarx.shop")) {
                view?.loadUrl(url)
                return true
            }
            // For external URLs, you could open them in browser if needed
            // For now, load them in WebView
            view?.loadUrl(url)
            return true
        }
        return false
    }
}
