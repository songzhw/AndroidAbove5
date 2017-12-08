package ca.six.demo.ctlay.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import ca.six.demo.R;

public class CtlaySample extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctlay_sample_simple_weight_two);


    }

}
/*
1. ctlay_sample_simple_weight : 模仿llay的weight. 一行三个Button, weight全为1
    (app:layout_constraintWidth_default="percent" app:layout_constraintWidth_percent="0.33". 但空白也得是View, 而且还有percentage, 是0.005的百分比)
2. ctlay_sample_simple_weight_two : 用chain. 那这就看需求, 得有不同chain style. 比如说我们最左最右没空隙, 那就是用SpreadInside.
    ()
3.
    ()
 */