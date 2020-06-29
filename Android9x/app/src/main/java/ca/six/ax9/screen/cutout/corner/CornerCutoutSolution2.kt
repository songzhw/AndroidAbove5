package ca.six.ax9.screen.cutout.corner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import ca.six.ax9.R
import kotlinx.android.synthetic.main.activity_cutout_corner.*

class CornerCutoutSolution2 : AppCompatActivity() {

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

//        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
//            println("szw insets = $insets")
//            insets
//        } //=> 只能看到cutout高132 (因为systemWindoInsets是[0, top=132, 0, 0]的Rect)

        window.decorView.post {
            val cutout = window.decorView.rootWindowInsets.displayCutout
            //=> 下面只是安全区域距离屏幕边缘的大小(单位px): szw margin: left = 0, top = 132, right = 0, bottom = 0
            println("szw margin: left = ${cutout.safeInsetLeft}, top = ${cutout.safeInsetTop}, right = ${cutout.safeInsetRight}, bottom = ${cutout.safeInsetBottom}")

            val rects = cutout.boundingRects
            for (rect in rects) {
                // 有一些Rect就是[0,0 - 0,0]. 这并不反应任何cutout信息, 所以要过滤掉
                if (rect.width() == 0 && rect.height() == 0) continue
                println("szw cutout size = $rect (width=${rect.width()}, height = ${rect.height()})")
                //=> 上面的结果是: szw cutout size = Rect(948, 0 - 1080, 132) (width=132, height = 132)

                val metrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(metrics)
                val screenWidth = metrics.widthPixels

                if(screenWidth == rect.right){
                    println("szw notch is on the right")
                    btnClose.setPadding(100, 0, 0, 0);
                }
            }
        }

        btnClose.setOnClickListener {
            println("szw close")
        }
    }

}