package six.ca.and8.notch

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_notch_info.*
import six.ca.and8.R
import java.lang.reflect.Field

class NotchInfoActivity : AppCompatActivity() {
	val BUILD_VERSION_P = 28
	val LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES = 1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_notch_info)

		if (Build.VERSION.SDK_INT >= BUILD_VERSION_P) {
			val lp = window.attributes
//			lp.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

			val clz = WindowManager.LayoutParams::class.java
			var field: Field? = null
			try {
				field = clz.getField("layoutInDisplayCutoutMode")
				field!!.set(lp, LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES)
			} catch (e: NoSuchFieldException) {
				e.printStackTrace()
			} catch (e: IllegalAccessException) {
				e.printStackTrace()
			}

		}

		getWindow().getDecorView().systemUiVisibility = (
				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						or View.SYSTEM_UI_FLAG_FULLSCREEN)
	}

	override fun onResume() {
		super.onResume()
		ivDisplayCutout.postDelayed({ info() }, 300)
	}

	override fun onConfigurationChanged(newConfig: Configuration?) {
		super.onConfigurationChanged(newConfig)
		ivDisplayCutout.postDelayed({ info() }, 300)
	}


	fun info() {

		//		val decorView = window.decorView
		//
		//		val windowInset = decorView.rootWindowInsets
		//		val displayCutout: DisplayCutout? = windowInset.displayCutout
		//		println("szw 01 : ${decorView.rootWindowInsets}")
		//		println("szw 02 : ${decorView.rootWindowInsets.displayCutout}")
		//		Log.e("szw", "SafeArea SafeInsetLeft:" + displayCutout?.safeInsetLeft)
		//		Log.e("szw", "SafeArea SafeInsetRight:" + displayCutout?.safeInsetRight)
		//		Log.e("szw", "SafeArea SafeInsetTop:" + displayCutout?.safeInsetTop)
		//		Log.e("szw", "SafeArea SafeInsetBottom:" + displayCutout?.safeInsetBottom)
		//
		//		val rects = displayCutout?.boundingRects
		//		if (rects == null || rects.size === 0) {
		//			Log.e("szw", "Have No Notch")
		//		} else {
		//			Log.e("szw", "Nonch Count:" + rects.size)
		//			for (rect in rects) {
		//				Log.e("szw", "Nonch Area：$rect")
		//			}
		//		}
	}
}