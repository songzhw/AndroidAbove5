package cn.six.sup.rv.cards;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.six.sup.R;



public class RvCardsAdapter extends RecyclerView.Adapter<RvCardsAdapter.ViewHolder> {
    public List<String> data;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public ViewHolder(CardView itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_rv_card_item);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_cards, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(data != null && data.size() > position){
           holder.tv.setText("[cards -- " + data.get(position) + "]");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}
