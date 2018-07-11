package ca.six.ctlay.anim.parallex.ad

import android.graphics.Rect
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import ca.six.ctlay.R
import ca.six.ctlay.utils.rv.compose.ItemView
import ca.six.ctlay.utils.rv.compose.OneTypesAdapter
import kotlinx.android.synthetic.main.activity_rv_parallex.*

/*
 TODO : 这个是要求广告位图片是有一定尺寸要求的. 好在这个好弄, 后台上传符合尺寸的图片就好
 若统一要换尺寸, 那item中的ivAd的高度就要变了.
*/

class ParallexRvAdDemo : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var onScrollListener: RecyclerView.OnScrollListener

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

        onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val offset = rvParallex.computeVerticalScrollOffset() // offset值, 会从0到rv滑动到底时(即2200多). 再滑到顶, offset又成了0
                val first = layoutManager.findFirstVisibleItemPosition()
                val last = layoutManager.findLastCompletelyVisibleItemPosition() //因为想至少让ad的初始部分先全显示出来, 再拖动才会有Parallex效果
                val rvHeight = layoutManager.height

                for (i in first..last) {
                    val itemView = layoutManager.findViewByPosition(i)  // 高度桓为630, 广告位的高度也是630 (但其drawable高为2100)
                    val adView = itemView.findViewById<ImageView>(R.id.ivItemParallexAd)
                    val guildlineView = itemView.findViewById<Guideline>(R.id.guildlineItemRvParallex2)
                    if (adView != null) { // 说明这一行是广告位

                        val itemHeight = itemView.height
                        val adDrawableRect = Rect()
                        adView.getDrawingRect(adDrawableRect)  //adDrawableRect.height()是2100
                        val maxPercent : Float = 0.5f * adDrawableRect.height() / itemHeight

                        val lastItemTop = rvHeight - itemHeight   // 1482
                        val itemTop = Math.max(0, itemView.top)  // 不要到负数, 在顶上还上推, 就不parallex了  [0, 1477]
                        val percent = 1.0f * itemTop / lastItemTop  // [0,1]之间

                        val guidePercent = maxPercent * (1 - percent)
                        val lp = guildlineView.layoutParams as ConstraintLayout.LayoutParams
                        lp.guidePercent = guidePercent
                        guildlineView.layoutParams = lp
                    }
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