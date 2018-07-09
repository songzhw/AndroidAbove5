package ca.six.ctlay.anim.circle

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.BounceInterpolator
import ca.six.ctlay.R
import ca.six.ctlay.utils.dp2px
import kotlinx.android.synthetic.main.activity_arc_menu.*

class ArcMenuDemo : AppCompatActivity() {
    private var isOpen = false
    private lateinit var menuViews: Array<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arc_menu)

        menuViews = arrayOf(fabMenuAlarm, fabMenuAutorenew, fabMenuBuild)

        fabTotal.setOnClickListener { view ->
            toggle()
        }

    }

    fun toggle() {
        if (isOpen) {
            close()
        } else {
            open()
        }
        isOpen = !isOpen
    }

    fun open() {
        startAnim(0, 135, true)
    }

    fun close() {
        startAnim(135, 0, false)
    }

    private fun startAnim(start: Int, end: Int, isVisible: Boolean) {
        val endRadius = end.dp2px(this)
        val anim = ValueAnimator.ofInt(start, endRadius)
        anim.duration = 1000
        anim.interpolator = BounceInterpolator()
        anim.addUpdateListener { valueAnimator ->
            val radius: Int = valueAnimator.animatedValue as Int
            menuViews.forEach { view ->
                if (isVisible) {
                    view.visibility = View.VISIBLE
                }
                val lp = view.layoutParams as ConstraintLayout.LayoutParams
                lp.circleRadius = radius
                view.layoutParams = lp
                // view.alpha = radius * 1.0f/ endRadius
            }
        }
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (!isVisible) {
                    menuViews.forEach { view ->
                        view.visibility = View.GONE
                    }
                }
            }
        })
        anim.start()
    }

}