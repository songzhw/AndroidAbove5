package cn.six.sup.rv.custom_layout_mgr.fixed_column.demo;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.Action;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.IFixedGridType;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.Name;
import cn.six.sup.rv.custom_layout_mgr.fixed_column.demo.entity.Numbera;


public class GridDemoAdapter extends RecyclerView.Adapter<RvViewHolder> {
    public List<IFixedGridType> data;

    @Override
    public int getItemViewType(int position) {
        IFixedGridType item = data.get(position);
        return item.getType();
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @LayoutRes int layoutResId = 0;
        switch (viewType){
            case IFixedGridType.TYPE_ACTION:
                layoutResId = R.layout.item_action;
                break;
            case IFixedGridType.TYPE_NUMBER:
                layoutResId = R.layout.item_number;
                break;
            case IFixedGridType.TYPE_NAME:
                layoutResId = R.layout.item_name;
                break;
        }
        RvViewHolder vh = RvViewHolder.createViewHolder(parent, layoutResId);
        return vh;
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        IFixedGridType item = data.get(position);
        if(item instanceof Name){
            Name name = (Name) item;
            holder.setText(R.id.tvItemName, name.name);
            holder.setText(R.id.tvItemSubName, name.subName);
        } else if( item instanceof Numbera){
            Numbera num = (Numbera) item;
            holder.setText(R.id.tvItemNumber, num.num+"");
        } else if( item instanceof Action){
            holder.setText(R.id.btnItemAction, "Action");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
