package cn.six.sup.design_lib.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by songzhw on 2016/2/24
 *
 * from : https://github.com/saulmm/CoordinatorBehaviorExample
 */
public class TopImageBehavior extends CoordinatorLayout.Behavior<ImageView> {

    public TopImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    // dependency is the Toolbar
    // @return true if the Behavior changed the child view's size or position, false otherwise
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        child.setX(child.getX() - 10);
        child.setY(child.getY() - 10);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.width = lp.width - 10;
        lp.height = lp.height - 10;
        child.setLayoutParams(lp);

        return true;
    }
}
