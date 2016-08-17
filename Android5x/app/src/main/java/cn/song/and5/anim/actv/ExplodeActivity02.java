package cn.song.and5.anim.actv;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import cn.song.and5.R;

public class ExplodeActivity02 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.setEnterTransition(new Explode());
        window.setExitTransition (new Fade());
        // window.setEnterTransition(new Slide());

        setContentView(R.layout.activity_anim_second);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}