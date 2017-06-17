package cn.six.sup.sample.clay;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvItemSwipeCallback;
import cn.six.sup.rv.RvItemSwipeListener;
import cn.six.sup.rv.composition.BaseComposedAdapter;
import cn.six.sup.rv.composition.BaseRow;
import cn.six.sup.rv.composition.UndoRow;
import cn.six.sup.rv.composition.demo.HeaderRow;
import cn.six.sup.rv.composition.demo.TwoTextRow;

import java.util.ArrayList;
import java.util.List;


public class ClayNewDemo extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener,
        RvItemSwipeListener, View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext_appbar_layout);

        configAppBar();
        configRvTop();
        configRvContent();
    }

    private void configRvContent() {
        rvContent = (RecyclerView) findViewById(R.id.rv_home);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setItemAnimator(new DefaultItemAnimator()); // system class for removing, adding, moving items

        items = new ArrayList<>();
        items.add(new HeaderRow("Sales"));
        items.add(new TwoTextRow("A1", "**A1**"));
        items.add(new TwoTextRow("A2", "**A2**"));
        items.add(new TwoTextRow("A3", "**A3**"));
        items.add(new TwoTextRow("A4", "**A4**"));
        items.add(new HeaderRow("Coupons"));
        items.add(new TwoTextRow("B1", "**B1**"));
        items.add(new TwoTextRow("B2", "**B2**"));
        items.add(new HeaderRow("Cards"));
        items.add(new TwoTextRow("C1", "**C1**"));
        items.add(new TwoTextRow("C2", "**C2**"));
        items.add(new TwoTextRow("C3", "**C3**"));

        adapter = new BaseComposedAdapter(items);
        rvContent.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RvItemSwipeCallback(this));
        itemTouchHelper.attachToRecyclerView(rvContent);

    }

    @Override
    public boolean isDragable(int position) {
        BaseRow row = items.get(position);
        if (row instanceof HeaderRow || row instanceof UndoRow) {
            return false;
        }
        return true;
    }

    // TODO: 2017-06-16 BUG: two undo, the second block the next item (not in the right place) 
    @Override
    public void onSwiped(int position) {
        // if there is another undo, the old undo row should disappear;
        if (lastDeletedRow != null && lastDeletedIndex > -1) {
            adapter.deleteItem(lastDeletedIndex);
            adapter.notifyItemRemoved(lastDeletedIndex);
        }

        // save data for undo
        lastDeletedRow = items.get(position);
        lastDeletedIndex = position;

        adapter.replaceItem(position, new UndoRow(this));//这不能直接操作list,要通过adapter的方法，不然会有NPE。因为adapter中还有个map需要维护。
        adapter.notifyItemChanged(position);
    }

    // click "undo" row, this onClick() method will get called
    @Override
    public void onClick(View v) {
        if (lastDeletedRow == null || lastDeletedIndex < 0) {
            return;
        }

        adapter.replaceItem(lastDeletedIndex, lastDeletedRow);
        adapter.notifyItemChanged(lastDeletedIndex);

        // reset the temporary data
        lastDeletedRow = null;
        lastDeletedIndex = -1;
    }

    private void configRvTop() {
        rvTop = (RecyclerView) findViewById(R.id.rv_home_top);
        rvTop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<ShowcaseItem> listTop = new ArrayList<>();
        listTop.add(new ShowcaseItem(R.drawable.ic_alarm, "Alarm"));
        listTop.add(new ShowcaseItem(R.drawable.ic_notifications, "Alert"));
        listTop.add(new ShowcaseItem(R.drawable.ic_pets, "Cat"));
        listTop.add(new ShowcaseItem(R.drawable.ic_alarm, "Dog"));
        listTop.add(new ShowcaseItem(R.drawable.ic_notifications, "Bird"));
        listTop.add(new ShowcaseItem(R.drawable.ic_pets, "Baby"));
        listTop.add(new ShowcaseItem(R.drawable.ic_alarm, "Dog2"));
        listTop.add(new ShowcaseItem(R.drawable.ic_notifications, "Bird2"));
        listTop.add(new ShowcaseItem(R.drawable.ic_pets, "Baby2"));

        adapterTop = new TopAdapter(R.layout.item_showcase);
        adapterTop.data = listTop;
        rvTop.setAdapter(adapterTop);

        rvTop.addOnItemTouchListener(new OnRvItemClickListener(rvTop) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("rvTop click : " + vh.getLayoutPosition());
            }
        });
    }

    private void configAppBar() {
        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        toolbarSize = (int) styledAttributes.getDimension(0, 0);

        appbar = (AppBarLayout) findViewById(R.id.extlay_home);
        appbar.addOnOffsetChangedListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        setTitle(""); // 不加这个， 默认就会有字样打出来
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        System.out.println("szw scroll range(2) = "+appbar.getTotalScrollRange()); //=> 260
//        System.out.println("szw : verticalOffest = " + verticalOffset); //=> 初始为0，一路变小， 直到 -260
        float percent = ((float) Math.abs(verticalOffset) / (float) appbar.getTotalScrollRange()); //0是最初状态， 1是全收缩起来的状态了
//        System.out.println("szw Percent = "+percent);

        if (percent == EXPANDED) {
            return;
        }

        if (percent != COLLAPSED) {
            int leftMargin = (int) (percent * toolbarSize);
            CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) rvTop.getLayoutParams();
            params.leftMargin = leftMargin + leftMargin / 4; // 1.25

            adapterTop.setAnimationFactor(percent);
        }

    }


    private int toolbarSize;

    private AppBarLayout appbar;
    private Toolbar toolbar;//得是UntouchableToolbar，不然收缩后， rv的点击或滑动都不能
    private RecyclerView rvContent, rvTop;
    private TopAdapter adapterTop;

    private List<BaseRow> items;
    private BaseComposedAdapter adapter;

    private BaseRow lastDeletedRow;
    private int lastDeletedIndex = -1;

    public static final float COLLAPSED = 1;
    public static final float EXPANDED = 0;

}

