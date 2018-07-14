package ca.six.ctlay.anim.detail_expand.order

import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import ca.six.ctlay.R
import kotlinx.android.synthetic.main.activity_place_order.*

class PlaceOrderActivity : AppCompatActivity() {
    private var isExpaned = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_nav_back)
        toolbar.setTitle("Place Order")
        toolbar.setTitleTextColor(Color.WHITE)


        /*
        虽然本Activity是用了import kotlinx.android.synthetic.main.activity_place_order.*
        但是在expanded状态下, 即在使用了R.layout.layout_place_order_expanded布局的情况下,
        这个viewCard仍是有效!  (这个要赞一个! )
        (其实是因为我们仍是在用activity_place_order的布局, 只不过是是变化了不同的constraints而已!!!!)
         */
        viewCard.setOnClickListener { v ->
            // 若已经是expanded状态, 那再点击就要转为缩回状态
            val layoutResId = if (isExpaned) R.layout.activity_place_order else R.layout.layout_place_order_expanded
            val constraintSet = ConstraintSet()
            constraintSet.clone(this, layoutResId)

            TransitionManager.beginDelayedTransition(ctlayPlaceOrder, ChangeBounds())
            constraintSet.applyTo(ctlayPlaceOrder)

            isExpaned = !isExpaned
        }


    }

}