package ca.six.android10x.bottombar

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity

class NormalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        println("szw visible = $rect")

        //=> 三按钮时, 132.0, 132  ; GestureNav时, 44.0, 44
        getSize("navigation_bar_height", "bottom")
        //=> 三按钮时, 66.0, 66  ; GestureNav时, 66.0, 66
        getSize("status_bar_height", "status")

        val resId = resources.getIdentifier("config_navBarInteractionMode", "integer", "android")
        if(resId > 0){
            val mode = resources.getInteger(resId)
            println("szw mode = $mode")
        } //0是3Button, 1是2Button(Android P里有的), 2是gestureNav(Android Q里有的)


        // these code would crash, the rootwindowinsets is null
//            val inset = window.decorView.rootWindowInsets.mandatorySystemGestureInsets
//            println("szw insert = $inset")

    }

    private fun getSize(resName: String, logName: String) {
        val resId = resources.getIdentifier(resName, "dimen", "android")
        if (resId > 0) {
            val v1 = resources.getDimension(resId)
            val v2 = resources.getDimensionPixelSize(resId)
            println("szw ${logName}1 = $v1, ${logName}2 = $v2")
        }
    }
}