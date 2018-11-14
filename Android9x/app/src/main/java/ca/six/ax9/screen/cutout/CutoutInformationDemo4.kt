package ca.six.ax9.screen.cutout

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.OnApplyWindowInsetsListener
import android.support.v4.view.ViewCompat
import android.support.v4.view.WindowInsetsCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.DisplayCutout
import android.view.View
import android.view.WindowManager
import ca.six.ax9.R
import kotlinx.android.synthetic.main.activity_display_cutout.*

// The final, successful version

class CutoutInformationDemo4 : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//开局就一张背景图
		setContentView(R.layout.activity_display_cutout)

		val params = window.attributes
		params.layoutInDisplayCutoutMode =
				WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

		getWindow().getDecorView().systemUiVisibility = (
				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						or View.SYSTEM_UI_FLAG_FULLSCREEN)


	}

	override fun onResume() {
		super.onResume()
		listen()
	}

	override fun onConfigurationChanged(newConfig: Configuration?) {
		super.onConfigurationChanged(newConfig)
		listen()
	}

	fun listen() {
		ViewCompat.setOnApplyWindowInsetsListener(ivDisplayCutout, object : OnApplyWindowInsetsListener {
			override fun onApplyWindowInsets(view: View?, insets: WindowInsetsCompat): WindowInsetsCompat {
				info()
				return insets
			}

		})
	}

	fun info() {

		val decorView = window.decorView

		val windowInset = decorView.rootWindowInsets
		val displayCutout: DisplayCutout? = windowInset.displayCutout
		println("szw 01 : ${decorView.rootWindowInsets}")
		println("szw 02 : ${decorView.rootWindowInsets.displayCutout}")
		Log.e("szw", "SafeArea SafeInsetLeft:" + displayCutout?.safeInsetLeft)
		Log.e("szw", "SafeArea SafeInsetRight:" + displayCutout?.safeInsetRight)
		Log.e("szw", "SafeArea SafeInsetTop:" + displayCutout?.safeInsetTop)
		Log.e("szw", "SafeArea SafeInsetBottom:" + displayCutout?.safeInsetBottom)

		val rects = displayCutout?.boundingRects
		if (rects == null || rects.size === 0) {
			Log.e("szw", "Have No Notch")
		} else {
			Log.e("szw", "Nonch Count:" + rects.size)
			for (rect in rects) {
				Log.e("szw", "Nonch Area：$rect")
			}
		}
	}
}
