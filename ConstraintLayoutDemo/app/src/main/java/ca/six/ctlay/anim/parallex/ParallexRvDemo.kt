package ca.six.ctlay.anim.parallex

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import ca.six.advk.utils.rv.OneAdapter
import ca.six.advk.utils.rv.RvViewHolder
import ca.six.ctlay.R
import kotlinx.android.synthetic.main.activity_rv_parallex.*

class ParallexRvDemo : AppCompatActivity() {

    private lateinit var onScrollListener: RecyclerView.OnScrollListener
    private lateinit var layoutManager: LinearLayoutManager

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


        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvParallex.layoutManager = layoutManager
        rvParallex.adapter = adapter

        PagerSnapHelper().attachToRecyclerView(rvParallex)



        onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstPos = layoutManager.findFirstVisibleItemPosition()
                val lastPos = layoutManager.findLastVisibleItemPosition()
                val offset = rvParallex.computeHorizontalScrollOffset()

                for (i in 0..lastPos - firstPos) {
                    val position = firstPos + i

                    val delta = offset - position * rvParallex.width
                    val percentage = delta.toFloat() / rvParallex.width

                    val itemView = layoutManager.findViewByPosition(position)
                    val guidelineView = itemView.findViewById<Guideline>(R.id.guildlineItemRvParallex)
                    val lp = guidelineView.layoutParams as ConstraintLayout.LayoutParams
                    lp.guidePercent = Math.max(0.2f, Math.min(0.8f, 0.5f - percentage))
                    guidelineView.layoutParams = lp

                }
            }
        }
        rvParallex.addOnScrollListener(onScrollListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        rvParallex.removeOnScrollListener(onScrollListener)
    }


}