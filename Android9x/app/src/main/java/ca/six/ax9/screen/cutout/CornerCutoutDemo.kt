package ca.six.ax9.screen.cutout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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

        window.decorView.systemUiVisibility =
                // app的绘制区域延伸至StatusBar区域，StatusBar上浮于app之上
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        // 隐藏StatusBar. 效果如同是加了WindowManager.LayoutParams.FLAG_FULLSCREEN
                        View.SYSTEM_UI_FLAG_FULLSCREEN

        btnClose.setOnClickListener {
            println("szw close")
        }
    }


}