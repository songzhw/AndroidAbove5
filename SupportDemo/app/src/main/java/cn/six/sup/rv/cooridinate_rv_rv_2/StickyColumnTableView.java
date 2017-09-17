package cn.six.sup.rv.cooridinate_rv_rv_2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

/**
 * Need to setAdapter(), then call refresh().
 */
public class StickyColumnTableView<T> extends LinearLayout {
    public int width = 7;

    private StickyColumnTableAdapter adapter;
    private RecyclerView rvLeft, rvRight;
    private ObservableHorizontalScrollView rightScrollView;
    private CoordinateRvScrollListener rvLeftScrollListener, rvRightScrollListener;
    private IStickyColumnTableInflater<T> binder;


    public StickyColumnTableView(Context context) {
        super(context);
        init(context);
    }

    public StickyColumnTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        this.setOrientation(HORIZONTAL);

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View leftView = inflater.inflate(R.layout.sub_multi_rv_left, this, false);
        View rightView = inflater.inflate(R.layout.sub_multi_rv_right, this, false);
        this.addView(leftView);
        this.addView(rightView);

        rvLeft = (RecyclerView) findViewById(R.id.rvMultiRvLeft);
        rvRight = (RecyclerView) findViewById(R.id.rvMultiRvRight);
        rightScrollView = (ObservableHorizontalScrollView) findViewById(R.id.hsvRight);


    }

    public void setAdapter(StickyColumnTableAdapter adapter) {
        this.adapter = adapter;
    }

    public void setBinder(IStickyColumnTableInflater<T> binder) {
        this.binder = binder;
    }

    public void setColumnNumber(int width){
        this.width = width;
        Context ctx = getContext();
        rvLeft.setLayoutManager(new LinearLayoutManager(ctx));
        rvRight.setLayoutManager(new GridLayoutManager(ctx, width));
    }

    public void refresh() {
        if (adapter == null) {
            return;
        }

        rvLeft.setAdapter(new OneAdapter<T>(R.layout.item_left, adapter.getLeftData()) {
            @Override
            protected void apply(RvViewHolder vh, T value, int position) {
                binder.bindLeft(vh, value, position);
            }
        });

        rvRight.setAdapter(new OneAdapter<T>(R.layout.item_right, adapter.getRightData()) {
            @Override
            protected void apply(RvViewHolder vh, T value, int position) {
                binder.bindRight(vh, value, position);
            }
        });

        rvLeftScrollListener = new CoordinateRvScrollListener(rvRight);
        rvLeft.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvRight, rvLeftScrollListener));

        rvRightScrollListener = new CoordinateRvScrollListener(rvLeft);
        rvRight.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvLeft, rvRightScrollListener));

        rightScrollView.setScrollViewListener(new ObservableHorizontalScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                rvLeft.removeOnScrollListener(rvLeftScrollListener);
                rvRight.removeOnScrollListener(rvRightScrollListener);
            }
        });

    }

}

