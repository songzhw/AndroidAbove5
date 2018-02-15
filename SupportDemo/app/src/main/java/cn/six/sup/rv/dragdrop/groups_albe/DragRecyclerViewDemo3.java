package cn.six.sup.rv.dragdrop.groups_albe;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;

import static cn.six.sup.rv.dragdrop.groups.Company.Country_CHINA;
import static cn.six.sup.rv.dragdrop.groups.Company.Country_US;
import static cn.six.sup.rv.dragdrop.groups.Company.TYPE_TITLE;

/**
* Created by songzhw on 2017-12-18.
*/
public class DragRecyclerViewDemo3 extends Activity implements RvItemDragSwipeListener3 {

    private List<Company3> data;
    private DragDropRvAdapter3 adapter;
    private RecyclerView rv;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_rv);

        rv = (RecyclerView) findViewById(R.id.rvDragDrop);
        rv.setLayoutManager(new LinearLayoutManager(this));
        this.data = new ArrayList<>();
        this.data.add(new Company3("Alibaba", Country_CHINA));
        this.data.add(new Company3("NetEase", Country_CHINA));
        this.data.add(new Company3("Tecent", Country_CHINA));
        this.data.add(new Company3("Xiaomi", Country_CHINA));
        this.data.add(new Company3("-- US --"));
        this.data.add(new Company3("Google", Country_US));
        this.data.add(new Company3("Apple", Country_US));
        this.data.add(new Company3("Microsoft", Country_US));

        adapter = new DragDropRvAdapter3(this.data);
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw long click = " + vh.toString());
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                moveToAnotherGroup(position);
            }
        });

        itemTouchHelper = new ItemTouchHelper(new RvItemDragSwipeCallback3(this));
        itemTouchHelper.attachToRecyclerView(rv);
    }

    private void moveToAnotherGroup(int position){
        int titlePosition = secondTitleIndex();
        int toPosition;
        if(position > titlePosition){
            toPosition = 0;
        } else {
            toPosition = titlePosition;
        }
        this.onMove(position, toPosition);
    }

    // =============== RvItemDragSwipeListener =================
    @Override
    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(data, fromPosition, toPosition);
        adapter.notifyItemMoved(fromPosition, toPosition);

        onPostMove(toPosition);
    }


    private void onPostMove(int toPosition) {
        int secondTitleIndex = secondTitleIndex();
        if (toPosition < secondTitleIndex) {
            data.get(toPosition).country = Country_CHINA;
        } else {
            data.get(toPosition).country = Country_US;
        }

        adapter.notifyItemChanged(toPosition);
    }


    @Override
    public boolean isDraggable(int position) {
        return  data.get(position).type != TYPE_TITLE;
    }

    private int secondTitleIndex() {
        int ret = -1;
        for (int i = 0; i < data.size(); i++) {
            Company3 company = data.get(i);
            if (company.type == TYPE_TITLE && company.name.contains("US")) {
                ret = i;
            }
        }
        System.out.println("szw secondTimePos = "+ret);
        return ret;
    }

    @Override
    public void onClearView() {
        System.out.println("szw actv clearView");
    }

    @Override
    public void onSelectionIsIdle(RecyclerView.ViewHolder viewHolder) {
        System.out.println("szw onSelectionIsIdel");

        //reorder the list
        int titlePosition = secondTitleIndex();
        List<Company3> tops = data.subList(0, titlePosition + 1);
        List<Company3> bottoms = data.subList(titlePosition + 1, data.size());

        Collections.sort(bottoms, (c1, c2) -> c1.name.compareTo(c2.name));

        List<Company3> ret = new ArrayList<>();
        ret.addAll(tops);
        ret.addAll(bottoms);

        Handler handler = new Handler();
        handler.post( () -> {
            adapter.data = ret;
            adapter.notifyDataSetChanged();

            itemTouchHelper.attachToRecyclerView(null);
            itemTouchHelper.attachToRecyclerView(rv);
        });
    }

}
