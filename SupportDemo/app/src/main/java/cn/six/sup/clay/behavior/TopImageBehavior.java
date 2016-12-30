package cn.six.sup.clay.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by songzhw on 2016/2/24
 *
 * from : https://github.com/saulmm/CoordinatorBehaviorExample
 * from : http://blog.csdn.net/tyk0910/article/details/53792012
 */

@Deprecated
// UI效果不好， 还待改进！(不好的原因，是child的位置的变化，跟depenedency无关，所以效果很突兀)
// 改进方法： 位置和dependency绑定， 让效果更好看
public class TopImageBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private int width, height, top, left;
    private int scrollRange;

    public TopImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        if(dependency instanceof AppBarLayout){
            final AppBarLayout ablay = (AppBarLayout) dependency;
            ablay.removeOnOffsetChangedListener(listener);
            ablay.addOnOffsetChangedListener(listener);
        }
        return dependency instanceof AppBarLayout;
    }

    private AppBarLayout.OnOffsetChangedListener listener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            scrollRange = appBarLayout.getTotalScrollRange();
        }
    };

    // dependency is the Toolbar
    // @return true if the Behavior changed the child view's size or position, false otherwise
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        float y = dependency.getY(); // getY() = mTop + getTranslationY();
        if(y == 0){
            width = child.getWidth();
            height = child.getHeight();
            top = child.getTop();
            left = child.getLeft();
        }

        // 1. 一开始时， top = 0, ty = 0 (ty: translationY)

        // 2. 下方的rv或nsv滑动，往上推时， appbarLayout变短， 这时ty始终为0，top变成负数, 且随着滑动越负越多(-6, -11, -21)
        // 下滑到iv没了， toolbar被pin住了时， 再怎么往上推， top也桓为-504 (即scrollRange的值), ty仍是总为0

        // 3. 经过上面ablay收到最小了。 rv或nsv再下拉， 因为scrollFlag是"scroll|exitUntilCollapsed"，
        // 所以直到rv或nsv到顶了， top也桓为-504 (即scrollRange的值), ty仍是总为0
        // 之后， rv或nsv再下拉， top就从-504开始变大了， 随着滑动， 值由-502， -493， ... 一直变到0 ， ty仍桓为0
        System.out.println("szw onDependentViewChanged() : top = "+dependency.getTop()+" ; ty = "+dependency.getTranslationY());

        float percent = Math.abs(y) / scrollRange;


        child.setX(child.getX() - 10);
        child.setY(child.getY() - 10);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.width = lp.width - 10;
        lp.height = lp.height - 10;
        child.setLayoutParams(lp);

        return true;
    }
}
