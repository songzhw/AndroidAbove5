package cn.six.sup.clay.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class DemoView extends View {
    private int centerX, centerY;
    private Paint paint;

    public DemoView(Context context) {
        super(context);
        init();
    }

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setTextSize(40);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("Dependency", 50, 130, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();


        // 加上action_move， 是因为不加的话， action_down时会有一种抖动的感觉
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
            layoutParams.leftMargin = x - centerX;
            layoutParams.topMargin = y - centerY;
            setLayoutParams(layoutParams);
        }

        return true;
    }
}
