package ca.six.ctlay.placeholder

import android.app.Activity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.Placeholder
import android.support.transition.TransitionManager
import android.view.View

import ca.six.ctlay.R


class PlaceHolderDemo : Activity(), View.OnClickListener {
    private var placeholderButton: Placeholder? = null
    private var ctlay: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_holder_demo)

        ctlay = findViewById(R.id.ctlay_place_holder)
        placeholderButton = findViewById(R.id.template_action_button)

        findViewById<View>(R.id.ib_save).setOnClickListener(this)
        findViewById<View>(R.id.ib_edit).setOnClickListener(this)
        findViewById<View>(R.id.ib_delete).setOnClickListener(this)
        findViewById<View>(R.id.ib_cancel).setOnClickListener(this)

    }

    override fun onClick(v: View) {
        val id = v.id
        TransitionManager.beginDelayedTransition(ctlay!!)
        placeholderButton!!.setContentId(id)
    }
}
