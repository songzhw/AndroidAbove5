package cn.song.and5.anim.actv;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;

import cn.song.and5.R;
import cn.song.and5.anim.transition.AnimSecondActivity;

public class ExplodeActivit01y extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_first);
    }

    public void click2ScaleUp(View v){
    }

    public void click2ThumbScaleUp(View v){
    }

    public void click2SceneTrans(View v){
    }

    public void click2SceneTransPair(View v){
    }


    public void click2Explode(View v){
        getWindow().setExitTransition(new Explode());
        startActivity( new Intent(this, ExplodeActivity02.class) );
    }
}