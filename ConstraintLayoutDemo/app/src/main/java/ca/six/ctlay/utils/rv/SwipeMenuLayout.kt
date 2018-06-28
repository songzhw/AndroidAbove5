package ca.six.advk.utils.rv

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout


class SwipeMenuLayout : FrameLayout {
    private var self: SwipeMenuLayout? = null
    private var dragger: ViewDragHelper? = null
    private var contentView: View? = null
    private var menuView: View? = null
    private var menuWidth: Int = 0
    private var draggedDistance: Int = 0
    var isOpen = false

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


    override fun onFinishInflate() {
        super.onFinishInflate()
        self = this
        contentView = getChildAt(0)
        menuView = getChildAt(1)
        dragger = ViewDragHelper.create(this, callback)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        menuWidth = menuView!!.measuredWidth
        contentView!!.layout(left, top, right, bottom)
        menuView!!.layout(right, top, right + menuWidth, bottom)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragger!!.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragger!!.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (dragger!!.continueSettling(true)) {
            invalidate()
        }
    }


    private val callback = object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child === contentView
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            if (left < -menuWidth) {
                return -menuWidth
            } else if (left > 0) {
                return 0
            }
            return left
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
            draggedDistance = left // menuWidth是246. 左滑拉出菜单时, left从0一直变到-246.  反之则是一直从负数变成0
            menuView!!.offsetLeftAndRight(dx)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) { //后两参是velocity
            if (releasedChild !== contentView) {
                return   //对menuView不要滑, 只要点击即可
            }

            val distance = Math.abs(draggedDistance) //大于0, 说明菜单拉出来了一部分.  若为0, 才表示菜单没出来.
            val threshold = menuWidth / 2
            if (distance > threshold) { //拉出了一半多, 这时松手, 要回到拉出的状态
                open()
            } else {
                close()
            }

        }
    }

    fun open() {
        isOpen = true
        if (dragger!!.smoothSlideViewTo(contentView!!, -menuWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(self)
        }
    }

    fun close() {
        isOpen = false
        if (dragger!!.smoothSlideViewTo(contentView!!, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(self)
        }
    }

}