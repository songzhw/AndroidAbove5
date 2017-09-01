package cn.six.sup.rv.coordinate_rv_rv.demo2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import cn.six.sup.R;
import cn.six.sup.rv.coordinate_rv_rv.MultiRvScrollListener;
import cn.six.sup.rv.coordinate_rv_rv.ObservableHorizontalScrollView;

/**
 * Need to setAdapter(), then call refresh().
 */
public class StickyColumnTableView extends LinearLayout {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 7;

    private StickyColumnTableAdapter adapter;
    private RecyclerView rvLeft, rvRight;
    private ObservableHorizontalScrollView rightScrollView;
    private CoordinateRvScrollListener rvLeftScrollListener, rvRightScrollListener;


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

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View leftView = inflater.inflate(R.layout.sub_multi_rv_left, this, false);
        View rightView = inflater.inflate(R.layout.sub_multi_rv_right, this, false);
        this.addView(leftView);
        this.addView(rightView);

        rvLeft = (RecyclerView)findViewById(R.id.rvMultiRvLeft);
        rvRight = (RecyclerView)findViewById(R.id.rvMultiRvRight);
        rightScrollView = (ObservableHorizontalScrollView)findViewById(R.id.hsvRight);
        rvLeft.setLayoutManager(new LinearLayoutManager(ctx));
        rvRight.setLayoutManager(new GridLayoutManager(ctx, WIDTH));

    }

    public void setAdapter(StickyColumnTableAdapter adapter) {
        this.adapter = adapter;
    }

    public void refresh(){
        if(adapter == null){
            return;
        }

    }


    private class CoordinateRvScrollListener extends MultiRvScrollListener {
        private RecyclerView rvOther;

        public CoordinateRvScrollListener(RecyclerView rvOther) {
            this.rvOther = rvOther;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            rvOther.scrollBy(dx, dy);
        }
    }


    // 在rvOther是idle时， 才会真的能移动
    private class CoordinateRvItemTouchListener implements RecyclerView.OnItemTouchListener {
        private RecyclerView rvOther;
        private CoordinateRvScrollListener scrollListener;
        private int lastY;

        public CoordinateRvItemTouchListener(RecyclerView rvOther,
                                             CoordinateRvScrollListener scrollListener) {
            this.rvOther = rvOther;
            this.scrollListener = scrollListener;
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            // fire the touch event, otherwise ,the onTouchEvent() will never get called
            if(rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                onTouchEvent(rv, e);
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            int action = e.getAction();
            if(action == MotionEvent.ACTION_DOWN && rvOther.getScrollState() == RecyclerView.SCROLL_STATE_IDLE){
                lastY = rv.getScrollY();
                rv.addOnScrollListener(scrollListener);
            } else {
                // if this touch is not a scrolling action, remove the scroll listener
                if(action == MotionEvent.ACTION_UP && rv.getScrollY() == lastY) {
                    rv.removeOnScrollListener(scrollListener);
                }
            }
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
    }
}
