package cn.six.sup.clay.appbar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.six.sup.R;


public class AppBarAnimAdapter extends RecyclerView.Adapter<AppBarAnimAdapter.ViewHolder> implements View.OnClickListener {

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onClick(final View v) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText("Test");
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public FrameLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            container = (FrameLayout) itemView.findViewById(R.id.container);
        }
    }
}