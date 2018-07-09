package ca.six.ctlay.anim.parallex.ad

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import ca.six.ctlay.R
import ca.six.ctlay.utils.rv.compose.ItemView
import ca.six.ctlay.utils.rv.compose.OneTypesAdapter
import kotlinx.android.synthetic.main.activity_rv_parallex.*

class ParallexRvAdDemo : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_parallex)

        val data = arrayListOf<ItemView>()
        data.add(SimpleTextItemView("This is item 1"))
        data.add(SimpleTextItemView("This is item 2"))
        data.add(SimpleTextItemView("This is item 3"))
        data.add(ImageItemView(R.drawable.wallpaper02))
        data.add(SimpleTextItemView("This is item 5"))
        data.add(SimpleTextItemView("This is item 6"))
        data.add(SimpleTextItemView("This is item 7"))
        data.add(SimpleTextItemView("This is item 8"))

        val adapter = OneTypesAdapter(data)

        layoutManager = LinearLayoutManager(this)   // vertical
        rvParallex.layoutManager = layoutManager
        rvParallex.adapter = adapter
        rvParallex.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


    }
}