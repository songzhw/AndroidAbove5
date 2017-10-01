package cn.six.sup.rv.stickyColumnTable.coordinate_rv_rv_in_rv;


import cn.six.sup.R;
import cn.six.sup.rv.ItemView;
import cn.six.sup.rv.RvViewHolder;

public class SimpleMenuRow implements ItemView {
    @Override
    public int getViewType() {
        return R.layout.item_menu;
    }

    @Override
    public void bind(RvViewHolder holder) {
        holder.setText(R.id.tvItemMenu, "id = "+ Math.random() * 10000);
    }
}
