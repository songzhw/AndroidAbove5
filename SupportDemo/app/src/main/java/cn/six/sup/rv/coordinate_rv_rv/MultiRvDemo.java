package cn.six.sup.rv.coordinate_rv_rv;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class MultiRvDemo extends Activity {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 7;
    private RecyclerView rvLeft;
    private RecyclerView rvRight;
    private ObservableHorizontalScrollView hsv;
    private CoordinateRvScrollListener rvLeftScrollListener, rvRightScrollListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_multi_rv);

        List<String> dataLeft = new ArrayList<>();
        for(int i = 1; i <= HEIGHT; i++){
            dataLeft.add(""+i);
        }

        List<String> dataRight = new ArrayList<>();
        int sum = HEIGHT * WIDTH;
        for(int i = 1; i <= sum; i++){
            dataRight.add(""+i);
        }

        rvLeft = (RecyclerView)findViewById(R.id.rvMultiRvLeft);
        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        rvLeft.setAdapter(new OneAdapter<String>(R.layout.item_left, dataLeft) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvItemSymbol, s);
            }
        });

        rvRight = (RecyclerView)findViewById(R.id.rvMultiRvRight);
        rvRight.setLayoutManager(new GridLayoutManager(this, WIDTH));
        rvRight.setAdapter(new OneAdapter<String>(R.layout.item_right, dataRight) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvItemDetails, s);
            }
        });

        rvLeftScrollListener = new CoordinateRvScrollListener(rvRight);
        rvLeft.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvRight, rvLeftScrollListener));

        rvRightScrollListener = new CoordinateRvScrollListener(rvLeft);
        rvRight.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvLeft, rvRightScrollListener));

        hsv = (ObservableHorizontalScrollView)findViewById(R.id.hsvRight);
        hsv.setScrollViewListener(new ObservableHorizontalScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                rvLeft.removeOnScrollListener(rvLeftScrollListener);
                rvRight.removeOnScrollListener(rvRightScrollListener);
            }
        });
    }

    // 当我滑动完了， 成idle了， recyclerView会移除此监听
    private class CoordinateRvScrollListener extends MultiRvScrollListener{
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
                rv.addOnScrollListener(scrollListener);
            }
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
    }

}





