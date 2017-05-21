package cn.six.sup.rv.composition.demo;

import android.view.ViewGroup;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.composition.BaseRow;


public class HeaderRow extends BaseRow {
    private String title, caption;

    public HeaderRow(String title, String caption) {
        this.title = title;
        this.caption = caption;
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
        holder.setText(R.id.title_text, title);
        holder.setText(R.id.caption_text, caption);
    }
}
