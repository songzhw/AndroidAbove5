package cn.six.sup.ctlay.anim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;

import cn.six.sup.R;

public class TransitionAnimDemo extends Activity {
    private ConstraintLayout ctlay;
    private boolean isBigPic = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_food_detail);

        ctlay = findViewById(R.id.ctlayFood);

        findViewById(R.id.ivFood).setOnClickListener( v -> {
            switchMode(isBigPic);
            isBigPic = !isBigPic;
        });
    }

    private void switchMode(boolean isBigPic) {
        int layoutId = isBigPic ? R.layout.actv_food_image : R.layout.actv_food_detail;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, layoutId);

        TransitionManager.beginDelayedTransition(ctlay, new ChangeBounds());
        constraintSet.applyTo(ctlay);
    }


}
