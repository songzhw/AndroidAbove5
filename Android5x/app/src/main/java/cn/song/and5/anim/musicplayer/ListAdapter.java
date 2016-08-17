package cn.song.and5.anim.musicplayer;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.song.and5.R;

/**
 * Created by songzhw on 2016-08-02
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.itemView;
        if(position % 2 == 1) {
            view.setBackgroundColor(0xFFC7EDCC);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
