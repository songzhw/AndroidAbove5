package cn.six.sup.rv.option_chain.demo2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import cn.six.sup.R;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyViewHolder>{
    private Context ctx;
    private List<String> data;

    public NumbersAdapter(Context ctx, List<String> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        System.out.println("szw inner onCreateViewHolder()");
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_only_tv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        System.out.println("szw inner onBindViewHolder("+position+")");
        holder.tv.setText(data.get(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tvInner);
        }
    }
}
