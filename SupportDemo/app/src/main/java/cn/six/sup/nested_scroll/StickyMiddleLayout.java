package cn.six.sup.nested_scroll;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;


public class StickyMiddleLayout extends LinearLayout implements NestedScrollingParent {
    private final static int TOP_CHILD_FLING_THRESHOLD = 3;

    private View topView;
    private ValueAnimator animator;

    private int topViewHeight;

    public StickyMiddleLayout(Context context) {
        super(context);
    }

    public StickyMiddleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        topView = getChildAt(0);
        View middleView = getChildAt(1);
        View bottomView = getChildAt(2);

        ViewGroup.LayoutParams lp = bottomView.getLayoutParams();
        lp.height = getMeasuredHeight() - middleView.getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec); // 再次计算. super.onMeasure()里会计入child.lp.height的

        setMeasuredDimension(getMeasuredWidth(), topView.getMeasuredHeight() + middleView.getMeasuredHeight() + bottomView.getMeasuredHeight());
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        View topChildView = getChildAt(0);
        topViewHeight = topChildView.getMeasuredHeight();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    // ============================= Scroll =============================
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        // onStartNestedScroll()中要指明我要关注哪个滑动方向
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * @param target: 经测试, 是那个可以自己滑动的RecyclerView
     * @param dy      : 往上推, 让topView不见时, dy是正数; 反之, 是负数
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // 滑动起来了, 我作为父layout, 要不要消耗掉这些滑动, 来做我自己相关的动画
        int scrollY = getScrollY();

        int scrollUp = -1;  // 手指的动作是向上拉, 即拉出底部来, 或说是让topView不可见. || 负数就是scroll up
        boolean isScrollingUp = dy > 0;
        boolean isScrollingDown = dy < 0;
        boolean canRvScrollUp = !target.canScrollVertically(scrollUp); //即表示底部仍有内容, 仍可以拉

        boolean scrolllUpCondition = isScrollingUp && scrollY < topViewHeight;
        boolean scrollDownCondition = isScrollingDown && scrollY > 0 && canRvScrollUp; //negative num is scrolling up

        if (scrolllUpCondition || scrollDownCondition) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }

    }

    /*
    以Nexus 6P手机为例,
    最顶了, 还scrollDown,  canScrollUp? = false ; canScrollDown? = true
    rv到底后, 还scrollUp,  canScrollUp? = true ; canScrollDown? = false
     */

    // ============================= fling =============================

    private int computeDuration(float velocityY) {
        final int distance;
        if (velocityY > 0) {
            distance = Math.abs(topView.getHeight() - getScrollY());
        } else {
            distance = Math.abs(topView.getHeight() - (topView.getHeight() - getScrollY()));
        }


        final int duration;
        velocityY = Math.abs(velocityY);
        if (velocityY > 0) {
            duration = 3 * Math.round(1000 * (distance / velocityY));
        } else {
            final float distanceRatio = (float) distance / getHeight();
            duration = (int) ((distanceRatio + 1) * 150);
        }

        return duration;

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        //如果是recyclerView 根据判断第一个元素是哪个位置可以判断是否消耗
        //这里判断如果第一个元素的位置是大于TOP_CHILD_FLING_THRESHOLD的
        //认为已经被消耗，在animateScroll里不会对velocityY<0时做处理
        if (target instanceof RecyclerView && velocityY < 0) {
            final RecyclerView recyclerView = (RecyclerView) target;
            final View firstChild = recyclerView.getChildAt(0);
            final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
            consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;
        }
        if (!consumed) {
            animateScroll(velocityY, computeDuration(0), consumed);
        } else {
            animateScroll(velocityY, computeDuration(velocityY), consumed);
        }
        return true;
    }

    private void animateScroll(float velocityY, final int duration, boolean consumed) {
        final int currentOffset = getScrollY();
        final int topHeight = topView.getHeight();
        if (animator == null) {
            animator = new ValueAnimator();
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (animation.getAnimatedValue() instanceof Integer) {
                        scrollTo(0, (Integer) animation.getAnimatedValue());
                    }
                }
            });
        } else {
            animator.cancel();
        }
        animator.setDuration(Math.min(duration, 600));

        if (velocityY >= 0) {
            animator.setIntValues(currentOffset, topHeight);
            animator.start();
        } else {
            //如果子View没有消耗down事件 那么就让自身滑倒0位置
            if (!consumed) {
                animator.setIntValues(currentOffset, 0);
                animator.start();
            }

        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) y = 0;
        if (y > topViewHeight) y = topViewHeight;
        if (y != getScrollY()) super.scrollTo(x, y);
    }


}

