package ca.six.ctlay.anim.detail_expand.order

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import ca.six.ctlay.R
import kotlinx.android.synthetic.main.activity_place_order.*

class PlaceOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_nav_back)
        toolbar.setTitle("Place Order")
        toolbar.setTitleTextColor(Color.WHITE)
    }
}