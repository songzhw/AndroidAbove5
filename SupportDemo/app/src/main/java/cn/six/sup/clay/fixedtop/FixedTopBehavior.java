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

    /* 5. NestScrolling逻辑
    滑动动作由child主动发起， parent收到回调并做出响应

    child准备滑动时通知parent，
         child调用了startNestedScroll(),
         而parent调用了onStartNestedScroll(), 决定是否需要配合child一起处理滑动。
              如果需要配合， 就还会再回调onNestedScrollAccepted()

    每次滑动前， child都要先询问parent是否需要滑动
         child调用了dispatchNestedPreScroll(),
         而parent调用了onNestedPreScroll(), parent可以在此先于child， 先滑动.

    child滑动后，
         child调用了dispatchNestedScroll(),
         而parent调用了onNestedScroll(). 就是child滑动完后， 还交待parent是否要后于child滑动

    滑动结束， parent调用 onStopNestedScroll()。
    */

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        if(dy < 0) {
            return; //只处理上滑的， 这时的dy是 > 0的
        }

        float leftY = child.getTranslationY() - dy;
        if(leftY > 0){
            child.setTranslationY(leftY);
            consumed[1] = dy; // 滑动了多少， 处理了多少。第0位是x， 第1位是y
        }
    }


}
