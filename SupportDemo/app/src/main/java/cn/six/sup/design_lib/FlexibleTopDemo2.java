package cn.six.sup.design_lib;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016/2/23
 */
public class FlexibleTopDemo2 extends Activity {
    private FlexibleTopDemo2 self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_flexible_two);
        self = this;

        CollapsingToolbarLayout toolbar = (CollapsingToolbarLayout) findViewById(R.id.ctlay_flex_top2);
        toolbar.setTitle("Wonderfuls");
        toolbar.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        toolbar.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色

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
