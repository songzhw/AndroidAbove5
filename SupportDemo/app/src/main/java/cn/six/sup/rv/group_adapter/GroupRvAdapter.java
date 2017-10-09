package cn.six.sup.rv.group_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.RvViewHolder;

/**
 * Created by songzhw on 2016-08-15
 * <p>
 * G, C should both implemetn IEntityInfo !!!
 */
public abstract class GroupRvAdapter<G extends IEntityInfo, C extends IEntityInfo>
        extends RecyclerView.Adapter<RvViewHolder> {
    public int parentLayoutId, childLayoutId;
    private List<IEntityInfo> data;
    private int groupType, childType;

    public GroupRvAdapter(int parentLayoutId, int childLayoutId) {
        this.parentLayoutId = parentLayoutId;
        this.childLayoutId = childLayoutId;
        data = new ArrayList<>();
    }

    public void setData(List<? extends IEntityInfo> d) {
        for (IEntityInfo parent : d) {
            groupType = parent.getType();
            data.add(parent);
            for (IEntityInfo child : parent.getChildren()) {
                childType = child.getType();
                data.add(child);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data == null) {
            return super.getItemViewType(position);
        }
        IEntityInfo item = data.get(position);
        return item.getType();
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RvViewHolder vh = null;
        if (viewType == groupType) {
            vh = RvViewHolder.createViewHolder(parent, parentLayoutId);
        } else if (viewType == childType) {
            vh = RvViewHolder.createViewHolder(parent, childLayoutId);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        if (data == null) {
            return;
        }
        IEntityInfo item = data.get(position);
        if (getItemViewType(position) == groupType) {
            applyGroup(holder, (G) item, position);
        } else if (getItemViewType(position) == childType) {
            applyChild(holder, (C) item, position);
        }
    }


    protected abstract void applyGroup(RvViewHolder vh, G item, int position);

    /* TODO if you want a Parent item(G) here. Just do the below job:
    public G parent;

    onBindViewHolder() { ..
        if( == groupType) { parent = (G) item; }
        if( == childType) { applyChild(holder, (C) item, parent, position); }
    }
    */
    protected abstract void applyChild(RvViewHolder vh, C item, int position);
}
