package cn.six.sup.clay.anim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.transition.TransitionManager;
import android.view.View;

import cn.six.sup.R;

// https://www.jianshu.com/p/575829baa39d
public class CtlayChainAnimDemo extends Activity implements View.OnClickListener {
    private ConstraintLayout ctlay;
    private ConstraintSet applySet = new ConstraintSet();
    private ConstraintSet resetSet = new ConstraintSet();
    private ConstraintSet centerSet = new ConstraintSet();
    private ConstraintSet childSet = new ConstraintSet();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctlay_anim);

        ctlay = findViewById(R.id.ctlayAnim);
        applySet.clone(ctlay);
        resetSet.clone(ctlay);
        centerSet.clone(ctlay); //没这句,  一applyTo(), 就全部child view都走到右上角并消失了
        childSet.clone(ctlay);

        findViewById(R.id.btnAnimApply).setOnClickListener(this);
        findViewById(R.id.btnAnimReset).setOnClickListener(this);
        findViewById(R.id.btnAnimCenter).setOnClickListener(this);

        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAnimReset) {
            TransitionManager.beginDelayedTransition(ctlay);
            resetSet.applyTo(ctlay);
        } else  if (id == R.id.btnAnimApply) {
            TransitionManager.beginDelayedTransition(ctlay);


            applySet.connect(R.id.button1, ConstraintSet.LEFT, R.id.ctlayAnim, ConstraintSet.LEFT, 0);
            applySet.connect(R.id.button1, ConstraintSet.RIGHT, R.id.button2, ConstraintSet.LEFT, 0);
            applySet.connect(R.id.button2, ConstraintSet.LEFT, R.id.button1, ConstraintSet.RIGHT, 0);
            applySet.connect(R.id.button2, ConstraintSet.RIGHT, R.id.button3, ConstraintSet.LEFT, 0);
            applySet.connect(R.id.button3, ConstraintSet.LEFT, R.id.button2, ConstraintSet.RIGHT, 0);
            applySet.connect(R.id.button3, ConstraintSet.RIGHT, R.id.ctlayAnim, ConstraintSet.RIGHT, 0);

            applySet.createHorizontalChain(R.id.button1, ConstraintSet.LEFT,
                    R.id.button3, ConstraintSet.RIGHT,
                    new int[]{R.id.button1, R.id.button3},
                    null, ConstraintWidget.CHAIN_PACKED);


            applySet.applyTo(ctlay);

        } else if (id == R.id.btnAnimCenter) {
            TransitionManager.beginDelayedTransition(ctlay);

        } else if (id == R.id.button1) {
            TransitionManager.beginDelayedTransition(ctlay);


        }
    }
}