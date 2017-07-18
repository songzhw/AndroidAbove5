package cn.six.sup.rv.coordinate_rv_rv;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.HorizontalScrollView;

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
    private HorizontalScrollView hsv;

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
        initRvLeftScrollListener();

        rvRight = (RecyclerView)findViewById(R.id.rvMultiRvRight);
        rvRight.setLayoutManager(new GridLayoutManager(this, WIDTH));
        rvRight.setAdapter(new OneAdapter<String>(R.layout.item_right, dataRight) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.tvItemDetails, s);
            }
        });
        initRvRightScrollListener();

        hsv = (HorizontalScrollView)findViewById(R.id.hsvRight);

    }

    private final RecyclerView.OnScrollListener rvLeftScrollListener = new MultiRvScrollListener(){
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy); // 当我滑动完了， 成idle了， rvLeft移除此监听
            rvRight.scrollBy(dx, dy); // rvLeft的移动， 要求rvRight也做相关动作。
        }
    };

    private final RecyclerView.OnScrollListener rvRightScrollListener = new MultiRvScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy); // 当我滑动完了， 成idle了， rvLeft移除此监听
            rvLeft.scrollBy(dx, dy); //rvRight的移动，也要求rvLeft动。 这和上面一比对，明显会无限循环。 所以要做处理。
        }
    };

    private void initRvLeftScrollListener(){
        rvLeft.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            int lastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                int action = e.getAction();
                // action_down保证只addOnScrollListener()一次，不会添加多个listener
                // rvRight是idle， 保证rvRight没有onScrollListener，这样就不会无限死循环
                if(action == MotionEvent.ACTION_DOWN && rvRight.getScrollState() == RecyclerView.SCROLL_STATE_IDLE){
                    lastY = rv.getScrollY();
                    rv.addOnScrollListener(rvLeftScrollListener);
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
        });
    }

    private void initRvRightScrollListener(){
        rvRight.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            int lastY;
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                int action = e.getAction();
                if(action == MotionEvent.ACTION_DOWN && rvLeft.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    lastY = rv.getScrollY();
                    rv.addOnScrollListener(rvRightScrollListener);
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
        });
    }
}


