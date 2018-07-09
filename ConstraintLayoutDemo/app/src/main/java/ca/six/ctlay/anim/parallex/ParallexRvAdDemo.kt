package ca.six.ctlay.anim.parallex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import ca.six.advk.utils.rv.OneAdapter
import ca.six.advk.utils.rv.RvViewHolder
import ca.six.ctlay.R
import kotlinx.android.synthetic.main.activity_rv_parallex.*

class ParallexRvAdDemo : AppCompatActivity(){
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_parallex)

        val data = Array(7) { i -> "This is item ${i+1}"}.asList()
        val adapter = object : OneAdapter<String>(R.layout.item_rv_parralex_ad, data) {
            override fun apply(vh: RvViewHolder, value: String, position: Int) {
                vh.setText(R.id.tvItemParallex2, value)
            }
        }

        layoutManager = LinearLayoutManager(this)   // vertical
        rvParallex.layoutManager = layoutManager
        rvParallex.adapter = adapter
        rvParallex.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


    }
}