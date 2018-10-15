package ca.six.ax9.screen_rotate

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import ca.six.ax9.R

class ScreenOrigDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_btn)
    }

    override fun onResume() {
        super.onResume()
        println("szw Activity01 onResume() ")
    }

    override fun onPause() {
        super.onPause()
        println("szw Activity01 onPause()")
    }

    override fun onStart() {
        super.onStart()
        println("szw Activity01 onStart()")
    }

    override fun onStop() {
        super.onStop()
        println("szw Activity01 onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("szw Activity01 onDestory()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        println("szw Activity01 : $newConfig")
    }

    fun onClickSimpleButton(v: View) {
    }

    fun onClickSimpleButton2(v: View) {
    }

}

/*
No configChange properties on <activity> in manifest

10-15 16:10:00.160 27673-27673/ca.six.ax9 I/System.out: szw Activity01 onStart()
10-15 16:10:00.166 27673-27673/ca.six.ax9 I/System.out: szw Activity01 onResume()
// rotate screen
10-15 16:11:32.274 27673-27673/ca.six.ax9 I/System.out: szw Activity01 onPause()
10-15 16:11:32.279 27673-27673/ca.six.ax9 I/System.out: szw Activity01 onStop()
10-15 16:11:32.280 27673-27673/ca.six.ax9 I/System.out: szw Activity01 onDestory()
10-15 16:11:32.377 27673-27673/ca.six.ax9 I/System.out: szw Activity01 onStart()
10-15 16:11:32.382 27673-27673/ca.six.ax9 I/System.out: szw Activity01 onResume()
 */

/*
add "android:configChanges="keyboardHidden|keyboard|orientation|screenSize"" in the manifest

10-15 16:13:32.541 28277-28277/ca.six.ax9 I/System.out: szw Activity01 onStart()
10-15 16:13:32.545 28277-28277/ca.six.ax9 I/System.out: szw Activity01 onResume()
// rotate screen
10-15 16:13:40.143 28277-28277/ca.six.ax9 I/System.out:
szw Activity01 : {1.15 302mcc220mnc [en_US,zh_CN_#Hans] ldltr sw411dp w683dp h387dp 560dpi nrml land finger -keyb/v/h -nav/h appBounds=Rect(168, 0 - 2560, 1440) s.23}


 */