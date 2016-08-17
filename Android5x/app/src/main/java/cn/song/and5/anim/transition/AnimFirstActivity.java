package cn.song.and5.anim.transition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.song.and5.R;

/**
 * @author songzhw
 * @date 2015/10/13
 * Copyright 2015 Six. All rights reserved.
 */
public class AnimFirstActivity extends Activity {
    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_first);

        iv = (ImageView) findViewById(R.id.iv_anim_first);
        tv = (TextView) findViewById(R.id.tv_anim_first);
    }

    public void click2ScaleUp(View v){
        int startX = iv.getWidth()/2;
        int startY = iv.getHeight()/2;
        ActivityOptions actvOpt = ActivityOptions.makeScaleUpAnimation(iv, startX, startY, 0, 0 );
        Intent intent = new Intent(this, AnimSecondActivity.class);
        startActivity(intent, actvOpt.toBundle());
    }

    public void click2ThumbScaleUp(View v){
        int startX = iv.getWidth()/2;
        int startY = iv.getHeight()/2;

        Drawable drawable = iv.getDrawable();
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();

        ActivityOptions opt = ActivityOptions.makeThumbnailScaleUpAnimation(iv, bitmap, startX, startY);
        Intent intent = new Intent(this, AnimSecondActivity.class);
        startActivity(intent, opt.toBundle());
    }

    public void click2SceneTrans(View v){
        ActivityOptions opt = ActivityOptions.makeSceneTransitionAnimation(this, iv, "lovely");
        Intent intent = new Intent(this, AnimSecondActivity.class);
        startActivity(intent, opt.toBundle());
    }

    public void click2SceneTransPair(View v){
        View v1 = iv; View v2 = tv;
        Pair<View, String> imagePair = Pair.create(v1, "lovely");
        Pair<View, String> textPair = Pair.create(v2, "test");

        ActivityOptions opt = ActivityOptions.makeSceneTransitionAnimation(this, imagePair, textPair);
        Intent intent = new Intent(this, AnimSecondActivity.class);
        startActivity(intent, opt.toBundle());
    }

    public void click2Explode(View v){
        Toast.makeText(this, "Please see Explode01Activity", Toast.LENGTH_SHORT).show();
    }
}
