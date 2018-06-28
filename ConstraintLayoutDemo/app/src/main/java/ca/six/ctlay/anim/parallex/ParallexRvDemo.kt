package ca.six.ctlay.anim.parallex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import ca.six.advk.utils.rv.OneAdapter
import ca.six.advk.utils.rv.RvViewHolder
import ca.six.ctlay.R
import kotlinx.android.synthetic.main.activity_rv_parallex.*

class ParallexRvDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_parallex)

        val data = intArrayOf(R.drawable.one_bg, R.drawable.two_bg, R.drawable.three_bg,
                R.drawable.fourth_bg, R.drawable.fifth_bg).asList()
        val adapter = object : OneAdapter<Int>(R.layout.item_rv_parralex, data) {
            override fun apply(vh: RvViewHolder, drawableId: Int, position: Int) {
                vh.setSrc(R.id.ivItemRvParallex, drawableId)
                vh.setText(R.id.tvItemRvParallex, "Item ${position + 1}")
            }
        }


        rvParallex.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvParallex.adapter = adapter

        PagerSnapHelper().attachToRecyclerView(rvParallex)


    }


}