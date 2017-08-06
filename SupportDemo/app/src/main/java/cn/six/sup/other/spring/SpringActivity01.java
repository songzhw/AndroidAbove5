package cn.six.sup.other.spring;

import android.app.Activity;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import cn.six.sup.R;

public class SpringActivity01 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_one);
        ImageView iv = (ImageView) findViewById(R.id.ivSpring01);

        SpringForce force = new SpringForce(0f)
                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_MEDIUM);
        SpringAnimation anim = new SpringAnimation(iv, SpringAnimation.TRANSLATION_Y)
                .setSpring(force);
        anim.start();
    }
}