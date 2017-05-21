package cn.six.sup.rv.composition;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BaseComposedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<BaseRow> items = new ArrayList<>();
    private HashMap<Integer, BaseRow> typeRowMap = new HashMap<>();

    public BaseComposedAdapter() {
        for(BaseRow row : items){
            typeRowMap.put(row.getViewType(), row);
        }
    }

    @Override
    public int getItemViewType(int position) {
        BaseRow row = items.get(position);
        return row.getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRow row = typeRowMap.get(viewType);
        return row.getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseRow row = items.get(position);
        row.bind();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
