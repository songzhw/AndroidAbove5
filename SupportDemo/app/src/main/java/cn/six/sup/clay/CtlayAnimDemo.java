package cn.six.sup.clay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.view.View;

import cn.six.sup.R;

// https://www.jianshu.com/p/575829baa39d
public class CtlayAnimDemo extends Activity implements View.OnClickListener {
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
        } else if (id == R.id.btnAnimCenter) {
            TransitionManager.beginDelayedTransition(ctlay);

            // 按钮全是600px宽度
            centerSet.constrainWidth(R.id.button1, 600);
            centerSet.constrainWidth(R.id.button2, 600);
            centerSet.constrainWidth(R.id.button3, 600);

            // 按钮全水平居中
            centerSet.centerHorizontally(R.id.button1, R.id.ctlayAnim);
            centerSet.centerHorizontally(R.id.button2, R.id.ctlayAnim);
            centerSet.centerHorizontally(R.id.button3, R.id.ctlayAnim);
            centerSet.applyTo(ctlay);
        } else if (id == R.id.button1) {
            TransitionManager.beginDelayedTransition(ctlay);

            childSet.setVisibility(R.id.button2, ConstraintSet.GONE);
            childSet.setVisibility(R.id.button3, ConstraintSet.GONE);

            childSet.clear(R.id.button1); //我想要把 view 上的所有 constraint 都清除掉
            // connect(): 给第一参上添加约束.
            childSet.connect(R.id.button1, ConstraintSet.LEFT, R.id.ctlayAnim, ConstraintSet.LEFT, 0);
            childSet.connect(R.id.button1, ConstraintSet.RIGHT, R.id.ctlayAnim, ConstraintSet.RIGHT, 0);
            childSet.connect(R.id.button1, ConstraintSet.TOP, R.id.ctlayAnim, ConstraintSet.TOP, 0);
            childSet.connect(R.id.button1, ConstraintSet.BOTTOM, R.id.ctlayAnim, ConstraintSet.BOTTOM, 0);
            childSet.applyTo(ctlay);

        }
    }
}
