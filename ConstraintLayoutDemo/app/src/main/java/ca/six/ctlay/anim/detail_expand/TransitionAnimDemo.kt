package ca.six.ctlay.anim.detail_expand

import android.app.Activity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View

import ca.six.ctlay.R

class TransitionAnimDemo : Activity() {
    private var ctlay: ConstraintLayout? = null
    private var isBigPic = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actv_food_detail)

        ctlay = findViewById(R.id.ctlayFood)

        findViewById<View>(R.id.ivFood).setOnClickListener {
            switchMode(isBigPic)
            isBigPic = !isBigPic
        }
    }

    private fun switchMode(isBigPic: Boolean) {
        val layoutId = if (isBigPic) R.layout.actv_food_image else R.layout.actv_food_detail
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, layoutId)

        TransitionManager.beginDelayedTransition(ctlay, ChangeBounds())
        constraintSet.applyTo(ctlay!!)
    }


}
