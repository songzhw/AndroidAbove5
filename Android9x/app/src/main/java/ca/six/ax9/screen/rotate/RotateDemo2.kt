package ca.six.ax9.screen.rotate

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ca.six.ax9.R

// 1. rotate后, 走layout-sw600dp, 还是走layout-sw600-land, 还是走layout-land?
// 2. rotate前后, status bar height是一样的吗
class RotateDemo2 : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_rotate_two)

		println("szw onCreate() : status bar height = ${getSystemStatusBarHeight()}")
	}

	override fun onConfigurationChanged(newConfig: Configuration?) {
		super.onConfigurationChanged(newConfig)
		println("szw onConfigChange() : status bar height = ${getSystemStatusBarHeight()}")
	}


	fun getSystemStatusBarHeight(): Int {
		var height = 0
		val resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android")
		if (resourceId > 0) {
			height = getResources().getDimensionPixelSize(resourceId)
		}
		return height
	}
}

/*
[log] (on Essenstial Ph-1 (has display cutout))

szw onCreate() : status bar height = 144
szw onConfigChange() : status bar height = 84
 */