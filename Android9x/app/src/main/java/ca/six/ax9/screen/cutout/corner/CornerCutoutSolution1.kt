package ca.six.ax9.screen.cutout.corner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import ca.six.ax9.R
import kotlinx.android.synthetic.main.activity_cutout_corner.*

class CornerCutoutSolution1  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cutout_corner)


        btnClose.setOnClickListener {
            println("szw close")
        }
    }

}