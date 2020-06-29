package ca.six.ax9.screen.cutout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import ca.six.ax9.R
import kotlinx.android.synthetic.main.activity_cutout_corner.*

class CornerCutoutDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cutout_corner)

        val params = window.attributes
        params.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        btnClose.setOnClickListener {
            println("szw close")
        }
    }


}