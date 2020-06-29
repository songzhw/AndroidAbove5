package ca.six.ax9.screen.cutout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ca.six.ax9.R
import kotlinx.android.synthetic.main.activity_cutout_corner.*

class CornerCutoutDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cutout_corner)

        btnClose.setOnClickListener {
            println("szw close")
        }
    }


}