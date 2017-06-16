package cn.six.sup.rv.composition;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;

/*
TODO It is easy to forget to update the items and typeRowMap together.
  Once I remove one item from items, I should remove it from typeRowMap too,
  but this is hard to remember
  <p>
  TODO will refactor it later. I want to delete the typeRowMap
*/
public class BaseComposedAdapter extends RecyclerView.Adapter<RvViewHolder> {
    protected List<BaseRow> items = new ArrayList<>();
    private HashMap<Integer, BaseRow> typeRowMap = new HashMap<>();

    public BaseComposedAdapter(List<BaseRow> items) {
        this.items = items;
        for(BaseRow row : items){
            typeRowMap.put(row.getViewType(), row); // 同id时，会覆盖前值
        }
    }

    @Override
    public int getItemViewType(int position) {
        BaseRow row = items.get(position);
        return row.getViewType();
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRow row = typeRowMap.get(viewType);
        return row.getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        BaseRow row = items.get(position);
        row.bind(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void replaceItem(int position, BaseRow newRow){
        typeRowMap.remove( items.get(position).getViewType() );
        typeRowMap.put(newRow.getViewType(), newRow);
        items.remove(position);
        items.add(position, newRow);
    }

}
