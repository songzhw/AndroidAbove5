package cn.six.sup.other.spring;

import android.app.Activity;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import cn.six.sup.R;

public class SpringActivity01 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_one);
        ImageView iv = (ImageView) findViewById(R.id.ivSpring01);

        final SpringForce force = new SpringForce(0f)
                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY) // 阻力。 振幅会逐渐下降。
                .setStiffness(SpringForce.STIFFNESS_MEDIUM); // 刚度。 多久回到原点。
        final SpringAnimation anim = new SpringAnimation(iv, SpringAnimation.TRANSLATION_Y)
                .setSpring(force);

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    anim.start();
                    return true;
                }
                return false;
            }
        });

    }
}