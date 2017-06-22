package cn.six.sup.rv.composition;

import android.view.View;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;

public class UndoRow extends BaseRow {
    private View.OnClickListener listener;

    public UndoRow(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected int getViewType() {
        return TYPE_UNDO;
    }

    @Override
    protected int getLayoutXmlRes() {
        return R.layout.item_undo;
    }

    @Override
    protected void bind(RvViewHolder holder) {
        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public boolean isDraggable() {
        return false;
    }
}
