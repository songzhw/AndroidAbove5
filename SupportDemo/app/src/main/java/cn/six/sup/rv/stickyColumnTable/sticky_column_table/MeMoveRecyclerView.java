package cn.six.sup.rv.stickyColumnTable.sticky_column_table;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**

 3个touch事件的被调用顺序:
 szw MeMoveRecyclerView             dispatchTouchEvent
 szw CoordinateRvItemTouchListener  onInterceptTouchEvent
 szw CoordinateRvItemTouchListener  onTouchEvent

 */
public class MeMoveRecyclerView extends RecyclerView {
    private RecyclerView rvOther;

    public MeMoveRecyclerView(Context context) {
        super(context);
    }

    public MeMoveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCoordinateRecyclerView(RecyclerView rvOther) {
        this.rvOther = rvOther;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (isEnabled()) {
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                rvOther.setEnabled(true);
            } else {
                rvOther.setEnabled(false);
            }
            return super.dispatchTouchEvent(ev);
        } else {
            return false;
        }

    }
}
