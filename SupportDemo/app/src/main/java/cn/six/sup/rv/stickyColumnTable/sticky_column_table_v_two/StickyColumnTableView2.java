package cn.six.sup.rv.stickyColumnTable.sticky_column_table_v_two;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import cn.six.sup.R;
import cn.six.sup.rv.OnRvItemClickListener;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class StickyColumnTableView2<T> extends FrameLayout {
    public static final int DEFAULT_COLUMN_NUMBER = 8;

    private StickyColumnTableAdapter2 adapter;
    private IStickyColumnTableInflater2<T> binder;
    private RecyclerView rvLeft, rvRight;


    public StickyColumnTableView2(Context context) {
        super(context);
        init(context, null);
    }

    public StickyColumnTableView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context ctx, AttributeSet attrs) {
        int width = DEFAULT_COLUMN_NUMBER;
        if (attrs != null) {
            TypedArray ta = ctx.obtainStyledAttributes(attrs, R.styleable.StickyColumnTableView);
            width = ta.getInteger(R.styleable.StickyColumnTableView_sctColumnNumber, DEFAULT_COLUMN_NUMBER);
            ta.recycle();
        }

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sticky_table_two, this, true);
        rvLeft = (RecyclerView) findViewById(R.id.rvMultiRvLeft2);
        rvRight = (RecyclerView) findViewById(R.id.rvMultiRvRight2);

        rvLeft.setLayoutManager(new LinearLayoutManager(ctx));
        rvRight.setLayoutManager(new GridLayoutManager(ctx, width));

        rvLeft.setNestedScrollingEnabled(false);
        rvRight.setNestedScrollingEnabled(false);

        rvRight.addOnItemTouchListener(new OnRvItemClickListener(rvRight) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw rvRight click " + vh.getLayoutPosition());
            }
        });
    }

    public void setAdapter(StickyColumnTableAdapter2 adapter) {
        this.adapter = adapter;
    }

    public void setBinder(IStickyColumnTableInflater2<T> binder) {
        this.binder = binder;
    }

    public void refresh(boolean isNestedScrollingEnabled) {
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



    }

    public void refresh(){
        refresh(true);
    }

}

