package cn.six.sup.rv.dragdrop.groups;

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

import static cn.six.sup.rv.dragdrop.groups.Company.Country_CHINA;
import static cn.six.sup.rv.dragdrop.groups.Company.Country_US;
import static cn.six.sup.rv.dragdrop.groups.Company.TYPE_TITLE;

/**
* Created by songzhw on 2017-12-18.
*/
public class DragRecyclerViewDemo2 extends Activity implements RvItemDragSwipeListener2 {

    private List<Company> data;
    private DragDropRvAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_rv);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvDragDrop);
        rv.setLayoutManager(new LinearLayoutManager(this));
        this.data = new ArrayList<>();
        this.data.add(new Company("-- China --"));
        this.data.add(new Company("Alibaba", Country_CHINA));
        this.data.add(new Company("NetEase", Country_CHINA));
        this.data.add(new Company("Tecent", Country_CHINA));
        this.data.add(new Company("Xiaomi", Country_CHINA));
        this.data.add(new Company("-- US --"));
        this.data.add(new Company("Google", Country_US));
        this.data.add(new Company("Apple", Country_US));
        this.data.add(new Company("Microsoft", Country_US));

        adapter = new DragDropRvAdapter2(this.data);
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw long click = " + vh.toString());
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw click + " + data.get(vh.getAdapterPosition()));
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RvItemDragSwipeCallback2(this));
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

        int secondTitleIndex = secondTitleIndex();
        if (toPosition < secondTitleIndex) {
            data.get(toPosition).country = Country_CHINA;
        } else {
            data.get(toPosition).country = Country_US;
        }

        adapter.notifyItemMoved(fromPosition, toPosition);
        adapter.notifyItemChanged(toPosition);
    }


    private int secondTitleIndex() {
        for (int i = 0; i < data.size(); i++) {
            Company company = data.get(i);
            if (company.type == TYPE_TITLE && company.name.contains("US")) {
                return i;
            }
        }
        return -1;
    }



}
