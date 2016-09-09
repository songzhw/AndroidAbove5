package cn.six.sup.rv.swipe_menu.rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.six.sup.R;

public class SwipeRvAdapter extends RecyclerView.Adapter<SwipeRvAdapter .ViewHolder> {

    private List<String> results;

    public SwipeRvAdapter (List<String> results){
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe_menu_demo, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.iv.setImageResource(R.drawable.ic_launcher);
        holder.tv.setText(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)  itemView.findViewById(R.id.tvRvItemSwipe);
            iv = (ImageView) itemView.findViewById(R.id.ivRvItemSwipe);
        }
    }
}