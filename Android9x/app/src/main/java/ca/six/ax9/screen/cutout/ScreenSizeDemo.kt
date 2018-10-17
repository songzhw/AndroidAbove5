package ca.six.ax9.screen.cutout

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.WindowManager

class ScreenSizeDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // getWidth(), getHeight()这两方法已经deprecated, 要用getSize()来代替
        val width1 = windowManager.defaultDisplay.width
        val height1 = windowManager.defaultDisplay.height

        val point = Point()
        windowManager.defaultDisplay.getSize(point)
        val width2 = point.x
        val height2 = point.y

        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        val width3 = outMetrics.widthPixels
        val height3 = outMetrics.heightPixels

        val rect4 = Rect()
        windowManager.defaultDisplay.getRectSize(rect4)
        val width4 = rect4.width()
        val height4 = rect4.height()


        val point5 = Point()
        windowManager.defaultDisplay.getRealSize(point5)
        val width5 = point5.x
        val height5 = point5.y





        println("szw width1 = $width1,  height1 = $height1")
        println("szw width2 = $width2,  height2 = $height2")
        println("szw width3 = $width3,  height3 = $height3")
        println("szw width4 = $width4,  height4 = $height4")
        println("szw width5 = $width5,  height5 = $height5")

    }


}

/*
从下面日志可以看出来, 前4种的结果是一样的
只有第五种在有cutout时, 把整个危险区域也算进来了, 其实就是整个手机的屏幕 (essenstial ph-1就是1312 * 2560的分辨率)
2018-10-17 12:42:26.075 14287-14287/ca.six.ax9 I/System.out: szw width1 = 1312,  height1 = 2279
2018-10-17 12:42:26.075 14287-14287/ca.six.ax9 I/System.out: szw width2 = 1312,  height2 = 2279
2018-10-17 12:42:26.075 14287-14287/ca.six.ax9 I/System.out: szw width3 = 1312,  height3 = 2279
2018-10-17 12:42:26.075 14287-14287/ca.six.ax9 I/System.out: szw width4 = 1312,  height4 = 2279
2018-10-17 12:42:26.075 14287-14287/ca.six.ax9 I/System.out: szw width5 = 1312,  height5 = 2560


//rotate screen
2018-10-17 12:42:40.665 14287-14287/ca.six.ax9 I/System.out: szw width1 = 2279,  height1 = 1312
2018-10-17 12:42:40.665 14287-14287/ca.six.ax9 I/System.out: szw width2 = 2279,  height2 = 1312
2018-10-17 12:42:40.665 14287-14287/ca.six.ax9 I/System.out: szw width3 = 2279,  height3 = 1312
2018-10-17 12:42:40.665 14287-14287/ca.six.ax9 I/System.out: szw width4 = 2279,  height4 = 1312
2018-10-17 12:42:40.665 14287-14287/ca.six.ax9 I/System.out: szw width5 = 2560,  height5 = 1312

 */