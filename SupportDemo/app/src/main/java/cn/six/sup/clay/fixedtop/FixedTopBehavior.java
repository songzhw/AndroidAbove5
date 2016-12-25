package cn.six.sup.clay.fixedtop;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016-12-25
 *
 * rv/sv上还有一个top view. 它们在滑动时， topView不变，还alpha减少， 最终被覆盖掉的过程。
 */

public class FixedTopBehavior extends CoordinatorLayout.Behavior<View> {

    private float topViewHeight;

    // 1. without this method, will get an error :
    //     "java.lang.NoSuchMethodException: <init> [class Context, interface AttributeSet]"
    public FixedTopBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        topViewHeight = context.getResources().getDimension(R.dimen.fixed_top_height);
    }

    // 2. 因为不是子view们之间的相互依赖，所以可以不写此方法
//    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {}


    // 3. clay虽不是flay的子类，但本质上仍是类似flay。 所以相当的排列也要自己注意了
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        // 本例中， 是rv的app_layout_behavior的值就是此behavior类。 所以这里的child其实就是一个rv

        // clay.Behavior基本上一定在clay中， 所以这里的cast是没问题的
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        // TODO: 2016-12-25 除了matchParent， 还要处理wrap_content与***dp !
        if (lp != null) {
            if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
                child.layout(0, 0, parent.getWidth(), parent.getHeight());
                child.setTranslationY(topViewHeight);
                return true; //true if the Behavior performed layout of the child view, false to request default layout behavior
            }
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /* 4.
    若是只写上面的layoutChild()， 就会没有滑动时遮蔽top view的效果
    同时，比如说rv要显示25个， 你滑动完就只显示22个。 因为你的rv的高度是全屏， 但现在你layout之后，
    rv只展示一部分， 所以滑到底时就没有显示出来最后的3个数据。
    具体效果若还不明白，可以注释掉下面的代码， 就看出来了。
    */

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 ;
    }

}
