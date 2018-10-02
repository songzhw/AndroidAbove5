package ca.six.ax9.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import ca.six.ax9.R
import ca.six.ax9.core.http.HttpEngine

class NoHttpDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_btn)
    }

    // Staring with Androipd P, cleartext support is disabled by default
    fun onClickSimpleButton(v: View) {
        HttpEngine.request("splash") { payload -> // worker thread
            val imgUrl = payload.get("imgUrl") as String
            println("szw onClick() ==> imgUrl = $imgUrl")
        }
    }

    fun onClickSimpleButton2(v: View) {

    }
}

/*
API: http://192.168.2.26:8899/splash

Response:
{
  code: 200,
  msg: "success",
  payload: {
    imgUrl: "http://192.168.2.26:8899/images/splash/splash4.jpg"
  }
}

 */