package cn.six.sup.clay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
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

        } else if (id == R.id.btnAnimReset) {

        }
    }
}
