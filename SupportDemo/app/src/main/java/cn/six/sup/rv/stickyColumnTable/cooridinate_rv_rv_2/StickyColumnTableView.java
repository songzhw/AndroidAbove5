package cn.six.sup.rv.stickyColumnTable.cooridinate_rv_rv_2;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.OneAdapter;

/**
 * Need to setAdapter(), then call refresh().
 */
public class StickyColumnTableView<T> extends LinearLayout {
    public static final int DEFAULT_COLUMN_NUMBER = 8;

    private StickyColumnTableAdapter adapter;
    private RecyclerView rvLeft, rvRight;
    private CoordinateRvScrollListener rvLeftScrollListener, rvRightScrollListener;
    private IStickyColumnTableInflater<T> binder;
    public boolean isSupportingVerticalScroll = true;

    public StickyColumnTableView(Context context) {
        super(context);
        init(context, null);
    }

    public StickyColumnTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context ctx, AttributeSet attrs) {
        this.setOrientation(HORIZONTAL);

        int width = DEFAULT_COLUMN_NUMBER;
        if (attrs != null) {
            TypedArray ta = ctx.obtainStyledAttributes(attrs, R.styleable.StickyColumnTableView);
            width = ta.getInteger(R.styleable.StickyColumnTableView_sctColumnNumber, DEFAULT_COLUMN_NUMBER);
        }

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View leftView = inflater.inflate(R.layout.sub_multi_rv_left, this, false);
        View rightView = inflater.inflate(R.layout.sub_multi_rv_right, this, false);
        this.addView(leftView);
        this.addView(rightView);

        rvLeft = (RecyclerView) findViewById(R.id.rvMultiRvLeft);
        rvRight = (RecyclerView) findViewById(R.id.rvMultiRvRight);

        rvLeft.setLayoutManager(new LinearLayoutManager(ctx));
        rvRight.setLayoutManager(new GridLayoutManager(ctx, width));

        rvLeft.addOnItemTouchListener(new OnRvItemClickListener(rvLeft) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw left : " + vh.getAdapterPosition() + " ; " + vh.getLayoutPosition());
            }
        });

        rvRight.addOnItemTouchListener(new OnRvItemClickListener(rvRight) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw right : " + vh.getAdapterPosition() + " ; " + vh.getLayoutPosition());
            }
        });
    }

    public void setAdapter(StickyColumnTableAdapter adapter) {
        this.adapter = adapter;
    }

    public void setBinder(IStickyColumnTableInflater<T> binder) {
        this.binder = binder;
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

        // if this view is in a rv, then you may have vertical scrolling issue. You should let this boolean be false.
        if(!isSupportingVerticalScroll) {
            return;
        }

        rvLeftScrollListener = new CoordinateRvScrollListener(rvRight);
        rvLeft.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvRight, rvLeftScrollListener));

        rvRightScrollListener = new CoordinateRvScrollListener(rvLeft);
        rvRight.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvLeft, rvRightScrollListener));

    }

}

