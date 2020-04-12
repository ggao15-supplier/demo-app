package com.example.morewindow

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout

class WindowView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var webView: WebView? = null

    init {
        orientation = VERTICAL
        webView = WebView(context).also {
            val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            it.layoutParams = layoutParams
            it.settings.allowContentAccess = true
            it.settings.javaScriptEnabled = true
            it.settings.setSupportZoom(false)
            it.settings.displayZoomControls = false
            it.settings.useWideViewPort = true
            it.settings.builtInZoomControls = false
            it.webChromeClient = object : WebChromeClient() {}
            it.webViewClient = object : WebViewClient() {}
        }
        addView(webView)

    }

    fun load(url: String) {
        webView?.loadUrl(url)
    }

}

