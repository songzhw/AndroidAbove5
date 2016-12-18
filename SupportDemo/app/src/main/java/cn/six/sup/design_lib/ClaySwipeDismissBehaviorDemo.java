package cn.six.sup.design_lib;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016/2/24
 */
public class ClaySwipeDismissBehaviorDemo extends AppCompatActivity implements SwipeDismissBehavior.OnDismissListener {
    private ClaySwipeDismissBehaviorDemo self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_behavior);
        self = this;

        CardView view = (CardView) findViewById(R.id.cv_swipe_behavior);

        SwipeDismissBehavior swipe = new SwipeDismissBehavior();
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        swipe.setListener(this);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        lp.setBehavior(swipe);
    }

    @Override
    public void onDismiss(View view) {
        Toast.makeText(self, "on dismiss", Toast.LENGTH_SHORT).show();
    }

    // STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
    @Override
    public void onDragStateChanged(int state) {
        Log.d("szw", "on onDragStateChanged");
    }
}
