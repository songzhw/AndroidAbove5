package ca.six.ax9.screen_rotate

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import ca.six.ax9.R

class ScreenRotateDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_btn)
    }

    override fun onResume() {
        super.onResume()
        println("szw Activity02 onResume() ")
    }

    override fun onPause() {
        super.onPause()
        println("szw Activity02 onPause()")
    }

    override fun onStart() {
        super.onStart()
        println("szw Activity02 onStart()")
    }

    override fun onStop() {
        super.onStop()
        println("szw Activity02 onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("szw Activity02 onDestory()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        println("szw Activity02 : $newConfig")
    }

    fun onClickSimpleButton(v: View) {
    }

    fun onClickSimpleButton2(v: View) {
    }

}