package cn.song.and5.anim.musicplayer;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.song.and5.R;
import cn.song.and5.anim.transition.AnimSecondActivity;

/**
 * Created by songzhw on 2016-08-02
 */
public class ListPage extends Activity {
    private ListPage self;
    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_music_list);
        self = this;

        tv = (TextView) findViewById(R.id.tvMusicDesp);
        iv = (ImageView) findViewById(R.id.ivMusicList);

        ListAdapter adapter = new ListAdapter();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rvMusicList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.btnMusicPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump();
            }
        });
    }

    private void jump(){
        View v1 = iv; View v2 = tv;
        Pair<View, String> imagePair = Pair.create(v1, "cover");
        Pair<View, String> textPair = Pair.create(v2, "desp");

        ActivityOptions opt = ActivityOptions.makeSceneTransitionAnimation(this, imagePair, textPair);
        Intent intent = new Intent(this, DetailPage.class);
        startActivity(intent, opt.toBundle());
    }

}
