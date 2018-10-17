package ca.six.ax9.screen.cutout

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.DisplayCutout
import android.view.View
import ca.six.ax9.R

class CutoutInformationDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //开局就一张背景图
        setContentView(R.layout.activity_display_cutout)

        val decorView = window.decorView

        decorView.post {
            val displayCutout : DisplayCutout? = decorView.rootWindowInsets.displayCutout
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

/*
发现一个疑似bug:

转屏后, 日志是:
szw: 安全区域距离屏幕左边的距离 SafeInsetLeft:null
szw: 安全区域距离屏幕右部的距离 SafeInsetRight:null
szw: 安全区域距离屏幕顶部的距离 SafeInsetTop:null
szw: 安全区域距离屏幕底部的距离 SafeInsetBottom:null
szw: 不是刘海屏
 */

/*
发现第二个疑似bug:
加上本工程的@style/fullscreen这个theme给本Activity后, 不论横还是竖屏, 结果都是:
szw: 安全区域距离屏幕左边的距离 SafeInsetLeft:null
szw: 安全区域距离屏幕右部的距离 SafeInsetRight:null
szw: 安全区域距离屏幕顶部的距离 SafeInsetTop:null
szw: 安全区域距离屏幕底部的距离 SafeInsetBottom:null
szw: 不是刘海屏
 */