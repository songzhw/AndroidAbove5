package ca.six.demo.ctlay.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.view.MotionEvent;
import android.view.View;

import ca.six.demo.R;

public class CtlaySample extends Activity {

    private Group group;

    private boolean isVisible = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctlay_sample_diff_visible);

        group = findViewById(R.id.group_jp);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isVisible) {
                group.setVisibility(View.GONE);
            } else {
                group.setVisibility(View.VISIBLE);
            }
            isVisible = !isVisible;

        }
        return super.onTouchEvent(event);
    }
}
/*
1. ctlay_sample_simple_weight : 模仿llay的weight. 一行三个Button, weight全为1
    (app:layout_constraintWidth_default="percent" app:layout_constraintWidth_percent="0.33". 但空白也得是View, 而且还有percentage, 是0.005的百分比)
2. ctlay_sample_simple_weight_two : 用chain. 那这就看需求, 得有不同chain style. 根据要宽度长达33%的特性, 得变成weight chain

3. ctlay_sample_weight_zero_one : 模仿llay中2个view, 一个weight为0, 一个weight为1.
    ()
4. ctlay_sample_diff_bg: 模仿一些布局就是为了背景和padding.
5. ctlay_sample_diff_visible: 模仿一些view一起变gone/visible的场景.  是使用ctlay的group
 */