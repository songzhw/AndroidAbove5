package cn.six.sup.sample.clay;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

import java.util.ArrayList;
import java.util.List;


public class ClayNewDemo extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext_appbar_layout);

        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        toolbarSize = (int) styledAttributes.getDimension(0, 0);

        appbar = (AppBarLayout)findViewById(R.id.extlay_home);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)appbar.getLayoutParams();
        params.setBehavior(new AppBarLayout.Behavior());

        toolbar = (Toolbar)findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        setTitle(""); // 不加这个， 默认就会有字样打出来

        rvTop = (RecyclerView)findViewById(R.id.rv_home_top);
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
                System.out.println("rvTop click : "+vh.getLayoutPosition());
            }
        });


        rvContent = (RecyclerView)findViewById(R.id.rv_home);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setItemAnimator(new DefaultItemAnimator()); // system class for removing, adding, moving items
        //        itemTouchHelper.attachToRecyclerViews(rv);

        OneAdapter adapter = new OneAdapter<String>(R.layout.item_rv_cards) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tv_rv_card_item, "item : ( " + s + " )");
            }
        };
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            data.add("" + i);
        }
        adapter.data = data;
        rvContent.setAdapter(adapter);

        appbar.addOnOffsetChangedListener(this);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        System.out.println("szw scroll range(2) = "+appbar.getTotalScrollRange()); //=> 260
//        System.out.println("szw : verticalOffest = " + verticalOffset); //=> 初始为0，一路变小， 直到 -260
        float percent = ((float) Math.abs(verticalOffset) / (float) appbar.getTotalScrollRange()); //0是最初状态， 1是全收缩起来的状态了
        System.out.println("szw Percent = "+percent);

        if(percent == EXPANDED){
            return;
        }

        if(percent != COLLAPSED){
            int leftMargin = (int)(percent * toolbarSize);
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

    public static final float COLLAPSED = 1;
    public static final float EXPANDED = 0;

}

