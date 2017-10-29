package cn.six.sup.rv.stickyColumnTable.touch;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two.IStickyColumnTableInflater2;
import cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two.StickyColumnTableAdapter2;
import cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two.StickyColumnTableView2;


public class StickyCoulmnTableRow extends BaseRow implements IStickyColumnTableInflater2<String> {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 7;

    @Override
    protected int getViewType() {
        return TYPE_STICKY_TABLE;
    }

    @Override
    protected int getLayoutXmlRes() {
        return R.layout.item_sticky_column_table;
    }

    @Override
    protected void bind(RvViewHolder holder) {
        StickyColumnTableView2<String> tableView = holder.getView(R.id.sctv_demo3);
        List<String> dataLeft = new ArrayList<>();
        for (int i = 1; i <= HEIGHT; i++) {
            dataLeft.add("" + i);
        }

        List<String> dataRight = new ArrayList<>();
        int sum = HEIGHT * WIDTH;
        for (int i = 1; i <= sum; i++) {
            dataRight.add("" + i);
        }

        StickyColumnTableAdapter2<String> adapter = new StickyColumnTableAdapter2<>(dataLeft, dataRight);
        tableView.setAdapter(adapter);
        tableView.setBinder(this);
        tableView.refresh(false);
    }

    @Override
    public boolean isDraggable() {
        return false;
    }

    @Override
    public void bindLeft(RvViewHolder vh, String s, int position) {
        vh.setText(R.id.tvItemSymbol, s);
    }

    @Override
    public void bindRight(RvViewHolder vh, String s, int position) {
        vh.setText(R.id.tvItemDetails, s);
    }
}
