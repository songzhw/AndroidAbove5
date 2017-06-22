package cn.six.sup.rv.composition.demo;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.composition.BaseRow;


public class HeaderRow extends BaseRow {
    private String title;

    public HeaderRow(String title) {
        this.title = title;
    }

    @Override
    protected int getViewType() {
        return TYPE_HEADER;
    }

    @Override
    protected int getLayoutXmlRes() {
        return R.layout.item_header;
    }

    @Override
    protected void bind(RvViewHolder holder) {
        holder.setText(R.id.tv_title, title);
    }

    @Override
    public boolean isDraggable() {
        return false;
    }

}