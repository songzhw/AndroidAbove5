package cn.six.sup.rv.simple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.six.sup.R;

public class RvSimpleAdapter extends RecyclerView.Adapter<RvSimpleAdapter.ViewHolder> {
    public List<String> data;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_rv_simple_item);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_simple, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(data != null && data.size() > position){
            holder.tvItem.setText("[szw -- " + data.get(position)+"]" );
            if(position % 2 == 1){
                holder.tvItem.setBackgroundColor(0xffC7EDCC);
            } else {
                holder.tvItem.setBackgroundColor(0xffffffff);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

}
