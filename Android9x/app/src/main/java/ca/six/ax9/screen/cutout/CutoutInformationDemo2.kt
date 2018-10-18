package ca.six.ax9.screen.cutout

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.WindowInsetsCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.DisplayCutout
import android.view.View
import android.view.WindowManager
import ca.six.ax9.R

/*
相对于CutoutInformationDemo类, 主要是本类多加了个配置:
             android:configChanges="keyboardHidden|keyboard|orientation|screenSize"



 */
// 要想图片伸到danger area, 就得三组(lp, systemUiVisibility, fullScreen的theme), 缺一不可
class CutoutInformationDemo2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //开局就一张背景图
        setContentView(R.layout.activity_display_cutout)

        val params = window.attributes
        params.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

//        getWindow().getDecorView().systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_FULLSCREEN)

    }

    override fun onResume() {
        super.onResume()
        info()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        info()
    }

    fun info(){

        val decorView = window.decorView

        decorView.post {
            val windowInset = decorView.rootWindowInsets
            val displayCutout: DisplayCutout? = windowInset.displayCutout
            println("szw 01 : ${decorView.rootWindowInsets}")
            println("szw 02 : ${decorView.rootWindowInsets.displayCutout}")
            Log.e("szw", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout?.safeInsetLeft)
            Log.e("szw", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout?.safeInsetRight)
            Log.e("szw", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout?.safeInsetTop)
            Log.e("szw", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout?.safeInsetBottom)

            val rects = displayCutout?.boundingRects
            if (rects == null || rects.size === 0) {
                Log.e("szw", "不是刘海屏")
            } else {
                Log.e("szw", "刘海屏数量:" + rects.size)
                for (rect in rects) {
                    Log.e("szw", "刘海屏区域：$rect")
                }
            }
        }

    }
}

/*
明显, 加上android:configChanges="keyboardHidden|keyboard|orientation|screenSize" 配置后, 整个Cutout信息就出错了:

竖屏:
安全区域距离屏幕左边的距离 SafeInsetLeft:0
安全区域距离屏幕右部的距离 SafeInsetRight:0
安全区域距离屏幕顶部的距离 SafeInsetTop:137
安全区域距离屏幕底部的距离 SafeInsetBottom:0

横屏:
安全区域距离屏幕左边的距离 SafeInsetLeft:0
安全区域距离屏幕右部的距离 SafeInsetRight:0
安全区域距离屏幕顶部的距离 SafeInsetTop:137
安全区域距离屏幕底部的距离 SafeInsetBottom:0
刘海屏数量:1
刘海屏区域：Rect(586, 0 - 726, 137)

再次竖屏:
安全区域距离屏幕左边的距离 SafeInsetLeft:0
安全区域距离屏幕右部的距离 SafeInsetRight:137
安全区域距离屏幕顶部的距离 SafeInsetTop:0
安全区域距离屏幕底部的距离 SafeInsetBottom:0
刘海屏数量:1
刘海屏区域：Rect(2423, 586 - 2560, 726)

看到了吧, 竖屏是其实应该是top为137, 横屏时应该是right为137
但现在明显混乱了.
 */