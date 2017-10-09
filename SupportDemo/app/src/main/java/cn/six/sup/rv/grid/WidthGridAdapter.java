package cn.six.sup.rv.grid;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;

public class WidthGridAdapter extends RecyclerView.Adapter<RvViewHolder> {
    public List<IWidthType> data;

    @Override
    public int getItemViewType(int position) {
        return data.get(position).teyp();
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IWidthType.TYPE_NUM) {
            return RvViewHolder.createViewHolder(parent, R.layout.item_number);
        } else {
            return RvViewHolder.createViewHolder(parent, R.layout.item_action);
        }
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        IWidthType item = data.get(position);
        int viewType = getItemViewType(position);
        if (viewType == IWidthType.TYPE_NUM) {
            holder.setText(R.id.tvItemNumber, item.value() );
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
