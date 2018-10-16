package ca.six.ax9.screen.rotate

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
[27] No configChange properties on <activity> in manifest

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
[27] add "android:configChanges="keyboardHidden|keyboard|orientation|screenSize"" in the manifest

10-15 16:23:45.482 3769-3769/ca.six.ax9 I/System.out: szw Activity01 onStart()
10-15 16:23:45.483 3769-3769/ca.six.ax9 I/System.out: szw Activity01 onResume()
//rotate
10-15 16:23:52.068 3769-3769/ca.six.ax9 I/System.out: szw Activity01 : {1.0 ?mcc?mnc [en_US,fr_FR] ldltr sw437dp w759dp h409dp 480dpi nrml long land finger -keyb/v/h -nav/h winConfig={ mBounds=Rect(0, 0 - 2279, 1312) mAppBounds=Rect(144, 0 - 2423, 1312) mWindowingMode=fullscreen mActivityType=standard} s.116}

*/


/*
[28] without "android:configChange" properpties in manifest
: Same
 */

/*
[28] android:configChanges="keyboardHidden|keyboard|orientation|screenSize"
:
10-15 16:21:43.920 3535-3535/ca.six.ax9 I/System.out: szw Activity01 onStart()
10-15 16:21:43.922 3535-3535/ca.six.ax9 I/System.out: szw Activity01 onResume()
//rotate
10-15 16:21:54.334 3535-3535/ca.six.ax9 I/System.out: szw Activity01 : {1.0 ?mcc?mnc [en_US,fr_FR] ldltr sw437dp w759dp h409dp 480dpi nrml long land finger -keyb/v/h -nav/h winConfig={ mBounds=Rect(0, 0 - 2279, 1312) mAppBounds=Rect(144, 0 - 2423, 1312) mWindowingMode=fullscreen mActivityType=standard} s.114}

 */