package cn.six.sup.rv.group;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.OnRvItemClickListener;


/**
 * Created by songzhw on 2016-07-13
 */
public class OldRvGroupDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView rv = new RecyclerView(this);
        setContentView(rv);

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("this is aData:" + i);
        }
        strings.add(3,"this is title: 1");
        strings.add(7,"this is title: 2");
        strings.add(16,"this is title: 3");
        OldRvGroupAdapter adapter = new OldRvGroupAdapter(this, strings);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw [ AdapterPosition = "+vh.getAdapterPosition()
                    +" ; LayoutPosition = "+vh.getLayoutPosition()
                    +" ; OldPosition = "+vh.getOldPosition() + " ] ");
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {

            }
        });

    }
}
