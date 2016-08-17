package cn.song.and5.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import cn.song.and5.R;

/**
 * @author hzsongzhengwang
 * @date 2015/10/12
 * Copyright 2015 Six. All rights reserved.
 */

// ViewAnimationUtil.createCircularReveal(View view, int centerX, int centerY, float startRadius, float endRadius) : returns a Animator object

public class ViewCircularRevealActivity extends Activity{
    private ImageView ivShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_test);

        ivShow = (ImageView) findViewById(R.id.iv_anim_show);
    }

    public void click2CreateViewAnimationUtil(View v){
        int centerX = ivShow.getWidth()/2;
        int certerY = ivShow.getHeight()/2;
        int maxBorder = Math.max(ivShow.getWidth(), ivShow.getHeight());

        // visible --> gone
        if(ivShow.getVisibility() == View.VISIBLE){
            //Note: it returns a Animator, rather than a Animation
            Animator anim = ViewAnimationUtils.createCircularReveal(ivShow, centerX, certerY, maxBorder, 0);
            anim.setDuration(1000);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ivShow.setVisibility(View.GONE);
                }
            });
            anim.start();
        }
        // gone --> visible
        else {
            Animator anim = ViewAnimationUtils.createCircularReveal(ivShow, centerX, certerY, 0, maxBorder);
            anim.setDuration(1000);
            ivShow.setVisibility(View.VISIBLE); // otherwise, the animator will not show ?
            anim.start();
        }
    }

    public void click2PathAnim(View v){
        Path path = new Path();
        path.moveTo(100, 100); path.lineTo(300, 100);path.lineTo(300, 300);
        path.close(); //If the current point is not equal to the first point of the contour, a line segment is automatically added.

        ivShow.setVisibility(View.VISIBLE);
        ObjectAnimator anim = ObjectAnimator.ofFloat(ivShow, View.X, View.Y, path);
        anim.setDuration(2000);
        anim.start();
    }

    public void click2ShowSvg(View v){
        ivShow.setImageResource(R.drawable.svg_icon_red_tri);
    }

    public void click2AnimSvg(View v){
        ivShow.setImageResource(R.drawable.bg_anim_svg);

        Drawable drawable = ivShow.getDrawable();
        if(drawable instanceof Animatable){
            Animatable animatable = (Animatable) drawable;
            animatable.start();
        }
    }

    public void click2LaunchScaleUp(View v){

    }
}
