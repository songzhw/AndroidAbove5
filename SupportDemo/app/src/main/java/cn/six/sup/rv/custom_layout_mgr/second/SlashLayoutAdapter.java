package cn.six.sup.rv.custom_layout_mgr.second;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016-11-12
 */

public class SlashLayoutAdapter extends RecyclerView.Adapter<SlashLayoutAdapter.ViewHolder> {
    public List<String> data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_slash, parent, false);
        SlashLayoutAdapter.ViewHolder holder = new SlashLayoutAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(data != null && data.size() > position) {
            int color1 = (int) (Math.random() * 255);
            int color2 = (int) (Math.random() * 255);
            int color3 = (int) (Math.random() * 255);
            holder.tv.setBackgroundColor(Color.argb(255, color1, color2, color3));
            holder.tv.setText(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvSlash);
        }
    }
}
