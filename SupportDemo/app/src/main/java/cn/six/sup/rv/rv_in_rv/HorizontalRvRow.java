package cn.six.sup.rv.rv_in_rv;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.one_adapter.OneAdapter;

import java.util.ArrayList;
import java.util.List;

public class HorizontalRvRow extends BaseRow {

    @Override
    protected int getViewType() {
        return TYPE_HORIZONTAL_RV;
    }

    @Override
    protected int getLayoutXmlRes() {
        return R.layout.item_horizontal_rv;
    }

    @Override
    protected void bind(RvViewHolder holder) {
        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 100; i ++){
            data.add("item "+i);
        }

        Context ctx = holder.itemView.getContext();
        RecyclerView rv = holder.itemView.findViewById(R.id.rv_item_horizontal_rv);
        rv.setLayoutManager(new GridLayoutManager(ctx, 10, LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new OneAdapter<String>(R.layout.item_tv_for_horizontal_rv, data) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                System.out.println("szw bind grid "+position);
                vh.setText(R.id.tv_for_horizontal_rv, s);
            }
        });

    }

    @Override
    public boolean isDraggable() {
        return false;
    }
}
