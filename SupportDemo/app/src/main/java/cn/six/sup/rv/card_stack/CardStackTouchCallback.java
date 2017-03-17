package cn.six.sup.rv.card_stack;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

import cn.six.sup.rv.one_adapter.OneAdapter;

/**
 * Created by songzhw on 2017-03-16
 */

public class CardStackTouchCallback<T> extends ItemTouchHelper.Callback {
    private OneAdapter<T> adapter;
    private ICardStackActionListener<T> listener;

    public CardStackTouchCallback(OneAdapter<T> adapter, ICardStackActionListener<T> listener) {
        this.adapter = adapter;
        this.listener = listener;
    }

    // card stack只关注 左、右 2个方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CardsLayoutManager) {
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    // 为了防止第二层和第三层卡片也能滑动，因此我们需要设置 isItemViewSwipeEnabled() 返回 false 。
    // 第一层不用担心，第一层的View有自己的onTouchListener
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // getMovementFlags()中的dragFlag是0, 所以onMove()中直接return false就好
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // 移除之前设置的 onTouchListener, 否则触摸滑动会乱了
        viewHolder.itemView.setOnTouchListener(null);
        // 删除相对应的数据
        int layoutPosition = viewHolder.getLayoutPosition();
        List<T> dataList = adapter.data;
        T remove = dataList.remove(layoutPosition);
        adapter.notifyDataSetChanged();
        // 卡片滑出后回调 OnSwipeListener 监听器
        if (listener != null) {
            listener.onSwiped(viewHolder, remove,
                    direction == ItemTouchHelper.LEFT ? CardStackConstant.LEFT : CardStackConstant.RIGHT);
        }
        // 当没有数据时回调 OnSwipeListener 监听器
        if (adapter.getItemCount() == 0) {
            if (listener != null) {
                listener.onSwipedClear();
            }
        }
    }
}
