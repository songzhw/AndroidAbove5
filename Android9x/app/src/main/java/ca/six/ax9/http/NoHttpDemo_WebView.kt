package ca.six.ax9.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ca.six.ax9.R
import kotlinx.android.synthetic.main.activity_no_http_webview.*

class NoHttpDemo_WebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_http_webview)

        webviewNoHttp.loadUrl("http://my_company.com/androidaccess_license")
    }
}