package ca.six.ctlay.anim.parallex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import ca.six.advk.utils.rv.OneAdapter
import ca.six.advk.utils.rv.RvViewHolder
import ca.six.ctlay.R
import kotlinx.android.synthetic.main.activity_rv_parallex.*

class ParallexRvDemo : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_parallex)

        val data  = IntArray(5, {it}).asList()
        val adapter = object : OneAdapter<Int>(R.layout.item_rv_parralex, data) {
            override fun apply(vh: RvViewHolder, t: Int, position: Int) {

            }
        }


        rvParallex.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvParallex.adapter = adapter

    }


}