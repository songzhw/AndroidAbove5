package cn.six.sup.clay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.view.View;

import cn.six.sup.R;


public class CtlayAnimDemo extends Activity implements View.OnClickListener {
    private ConstraintLayout ctlay;
    private ConstraintSet applySet = new ConstraintSet();
    private ConstraintSet resetSet = new ConstraintSet();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctlay_anim);

        ctlay = findViewById(R.id.ctlayAnim);
        applySet.clone(ctlay);
        resetSet.clone(ctlay);

        findViewById(R.id.btnAnimApply).setOnClickListener(this);
        findViewById(R.id.btnAnimReset).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAnimApply) {
            // btn1的marginLeft变成了8dp
            // bnt2因为"其左边依靠的是btn1", 所以其水平也跟着变化
            // btn2的左边是依赖的parent, 所以其水平位置不变
            TransitionManager.beginDelayedTransition(ctlay);
            applySet.setMargin(R.id.button1, ConstraintSet.LEFT, 8);
            applySet.applyTo(ctlay);
        } else if (id == R.id.btnAnimReset) {
            TransitionManager.beginDelayedTransition(ctlay);
            resetSet.applyTo(ctlay);
        }
    }
}
