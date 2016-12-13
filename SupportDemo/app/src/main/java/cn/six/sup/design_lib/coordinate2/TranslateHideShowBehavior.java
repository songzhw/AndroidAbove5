package cn.six.sup.design_lib.coordinate2;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by songzhw on 2016-07-11
 */
public class TranslateHideShowBehavior extends CoordinatorLayout.Behavior<View> {

    public TranslateHideShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    // @return true if the Behavior changed the child view's size or position, false otherwise
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        // dependency : AppBarLayout
        int appBarTop = dependency.getTop(); // 上推消失时， 数值由0变-190多。 反之出现时，又从负数变成0.
        float translationY = Math.abs(appBarTop);

        // child : FloatingActionBar and bottom textView are using this behavior
        // so fab and bottom textView is the "child"
        child.setTranslationY(translationY);
        return true;
    }
}
