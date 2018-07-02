package ca.six.ctlay.anim.tao_detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ca.six.ctlay.R

// 主要效果还是用ConstraintSet, 替换2个不同的ctlay布局, 并用TransitionManager来做动画(ChangeBounds)
// 整体代码类似 detail_expand.TransitionAnimDemo

class TaoItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tao_item)
    }
}