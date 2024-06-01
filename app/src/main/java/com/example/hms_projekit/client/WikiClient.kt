package com.example.hms_projekit.client

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.hms_projekit.view.description.WikiPageFragment

class WikiClient : WebViewClient() {
    //varsayılan tarayıcıya yönlendirilmemesi için client oluşturduk
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        view!!.loadUrl(request!!.url.toString())
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        with(WikiPageFragment.viewModel){
            loading.value = true
        }
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        with(WikiPageFragment.viewModel){
            loading.value = false
        }
        super.onPageFinished(view, url)
    }


    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        if (error?.description!!.contains("INTERNET_DISCONNECTED")) {
            with(WikiPageFragment.viewModel){
                loading.value = false
                this.error.value = true
            }
        }
        Log.d("errorWebView", "${error?.errorCode}, ${error?.description}")
        super.onReceivedError(view, request, error)
    }
}