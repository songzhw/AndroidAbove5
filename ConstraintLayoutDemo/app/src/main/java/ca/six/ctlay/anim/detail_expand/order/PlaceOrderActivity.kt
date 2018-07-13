package ca.six.ctlay.anim.detail_expand.order

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import ca.six.ctlay.R

class PlaceOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_place_order)
    }
}