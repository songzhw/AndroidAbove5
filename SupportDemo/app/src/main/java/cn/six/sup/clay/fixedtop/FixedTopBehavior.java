package cn.six.sup.clay.fixedtop;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by songzhw on 2016-12-25
 *
 * rv/sv上还有一个top view. 它们在滑动时， topView不变，还alpha减少， 最终被覆盖掉的过程。
 */

public class FixedTopBehavior extends CoordinatorLayout.Behavior<View> {

    // without this method, will get an error :
    //     "java.lang.NoSuchMethodException: <init> [class Context, interface AttributeSet]"
    public FixedTopBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}
