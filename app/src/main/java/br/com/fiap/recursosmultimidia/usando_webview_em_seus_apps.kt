package br.com.fiap.recursosmultimidia


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient

class Usando_WebView_em_seus_Apps : AppCompatActivity() {

    var mywebview: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usando_webview_em_seus_apps)

        mywebview = findViewById<WebView>(R.id.webview)

        mywebview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        mywebview!!.getSettings().setJavaScriptEnabled(true)

        mywebview!!.loadUrl("http://www.gamadeveloper.com.br/")
    }
}