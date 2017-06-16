package cn.six.sup.rv.dragdrop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvItemDragSwipeCallback;
import cn.six.sup.rv.RvItemDragSwipeListener;


/**
 * Created by songzhw on 2016-06-09.
 */
public class DragRecyclerViewDemo extends Activity implements RvItemDragSwipeListener {

    private List<String> data;
    private DragDropRvAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_rv);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvDragDrop);
        rv.setLayoutManager(new LinearLayoutManager(this));
        this.data = new ArrayList<>();this.data.add("Alibaba");
        this.data.add("NetEase");this.data.add("Google");this.data.add("Apple");

        adapter = new DragDropRvAdapter(this.data);
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw long click = "+vh.toString());
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw click = "+vh.toString());
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RvItemDragSwipeCallback(this));
        itemTouchHelper.attachToRecyclerView(rv);
    }


    // =============== RvItemDragSwipeListener =================
    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwiped(int position) {
        data.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onFinishDrag() {

    }
}
