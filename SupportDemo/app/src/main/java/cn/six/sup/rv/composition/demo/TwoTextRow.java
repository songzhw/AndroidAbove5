package cn.six.sup.rv.composition.demo;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.composition.BaseRow;


public class TwoTextRow extends BaseRow {
    private String leftText, rightText;

    public TwoTextRow(String leftText, String rightText) {
        this.leftText = leftText;
        this.rightText = rightText;
    }

    @Override
    protected int getViewType() {
        return TYPE_TWO_TEXT;
    }

    @Override
    protected int getLayoutXmlRes() {
        return R.layout.item_two_text;
    }

    @Override
    protected void bind(RvViewHolder holder) {
        holder.setText(R.id.tvLeft, leftText);
        holder.setText(R.id.tvRight, rightText);
    }

    @Override
    public boolean isDraggable() {
        return true;
    }
}
