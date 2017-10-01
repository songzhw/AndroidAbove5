package cn.six.sup.rv.custom_layout_mgr.fixed_column.demo;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import cn.six.sup.rv.RvViewHolder;


public class GridDemoAdapter extends RecyclerView.Adapter<RvViewHolder> {


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
