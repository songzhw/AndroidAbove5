package cn.six.sup.design_lib;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016/2/23
 */
public class FlexibleTopDemo extends Activity {

    private FlexibleTopDemo self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_flexible);
        self = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_flx_top);
        toolbar.setTitle("Wonderfuls");
        toolbar.setSubtitle("Great Wall, Zeus");

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_flx_top);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyRecyclerViewAdapter());
    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(new TextView(self));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tv.setText("TaskStatus " + position);
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
            tv.setTextSize(40);
        }
    }
}
