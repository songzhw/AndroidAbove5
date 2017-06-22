package cn.six.sup.rv.composition;

import android.view.View;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;

public class ClothRow extends BaseRow {
    private String name, desp, action;
    private View.OnClickListener listener;

    public ClothRow(String name, String desp, String action, View.OnClickListener listener) {
        this.name = name;
        this.desp = desp;
        this.action = action;
        this.listener = listener;
    }

    @Override
    protected int getViewType() {
        return TYPE_CLOTH;
    }

    @Override
    protected int getLayoutXmlRes() {
        return R.layout.item_cloth;
    }

    @Override
    protected void bind(RvViewHolder holder) {
        holder.setText(R.id.tv_cloth_desp, desp);
        holder.setText(R.id.tv_cloth_title, name);
        holder.setText(R.id.btn_cloth_buy, action);
        holder.setClickListener(R.id.btn_cloth_buy, listener);
    }

    @Override
    public boolean isDraggable() {
        return false;
    }
}
