package others.swirl.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

import cn.six.a6x.R;
import others.swirl.SwirlView;


public class SwirlSampleActivity extends Activity
        implements RadioGroup.OnCheckedChangeListener {
    SwirlView swirlView;
    RadioGroup stateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swirl_sample);
        swirlView = (SwirlView) findViewById(R.id.swirl);

        stateView = (RadioGroup) findViewById(R.id.state);
        stateView.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.off:
                swirlView.setState(SwirlView.State.OFF);
                break;
            case R.id.on:
                swirlView.setState(SwirlView.State.ON);
                break;
            case R.id.error:
                swirlView.setState(SwirlView.State.ERROR);
                break;
            default:
                throw new IllegalArgumentException("Unexpected checkedId: " + checkedId);
        }
    }
}
