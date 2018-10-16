package ca.six.ax9.screen_rotate

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import ca.six.ax9.R

class ScreenRotateDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //开局就一张背景图
        setContentView(R.layout.activity_display_cutout)

        val decorView = window.decorView

        decorView.post {
            val displayCutout = decorView.rootWindowInsets.displayCutout
            Log.e("szw", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.safeInsetLeft)
            Log.e("szw", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.safeInsetRight)
            Log.e("szw", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.safeInsetTop)
            Log.e("szw", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.safeInsetBottom)

            val rects = displayCutout.boundingRects
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
对于Essential PH-1手机来说, 其顶部有一个水滴状的cutout.

在PH-1上运行本Activity, 数据是:
    安全区域距离屏幕左边的距离 SafeInsetLeft:0
    安全区域距离屏幕右部的距离 SafeInsetRight:0
    安全区域距离屏幕顶部的距离 SafeInsetTop:137
    安全区域距离屏幕底部的距离 SafeInsetBottom:0
    刘海屏数量:1
    刘海屏区域：Rect(586, 0 - 726, 137)

也就是说安全区域在下方, 距离上方是137px
而cutout在危险区域中也不是全占了, 只占[586, 726]之间的部分 (垂直是占了[0, 137]的部分)

 */