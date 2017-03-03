package cn.song.and5;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.song.and5.anim.actv.ExplodeActivit01y;
import cn.song.and5.anim.musicplayer.ListPage;
import cn.song.and5.anim.transition.AnimFirstActivity;
import cn.song.and5.anim.ViewCircularRevealActivity;
import cn.song.and5.snackbar.SnackBarTestActivity;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickWaterfall(View v){
//        Intent it = new Intent(this, RvWaterFallActivity.class);
//        startActivity(it);
    }

    public void clickAnimTest(View v){
        Intent it = new Intent(this, ViewCircularRevealActivity.class);
        startActivity(it);
    }

    public void clickActvTrans(View v){
        Intent it = new Intent(this, AnimFirstActivity.class);
        startActivity(it);
    }

    public void clickViewTrans(View v){
        Intent it = new Intent(this, ViewCircularRevealActivity.class);
        startActivity(it);
    }

    public void clickSimpleRv(View v){
//        Intent it = new Intent(this, RvSimpleTestActivity.class);
//        startActivity(it);
    }

    public void clickCardRv(View v){
//        Intent it = new Intent(this, RvCardsActivity.class);
//        startActivity(it);
    }

    public void clickSnackBar(View v){
        Intent it = new Intent(this, SnackBarTestActivity.class);
        startActivity(it);
    }

    public void clickExplode(View v){
        Intent it = new Intent(this, ExplodeActivit01y.class);
        startActivity(it);
    }

    public void clickMusic(View v){
        Intent it = new Intent(this, ListPage.class);
        startActivity(it);
    }

}

