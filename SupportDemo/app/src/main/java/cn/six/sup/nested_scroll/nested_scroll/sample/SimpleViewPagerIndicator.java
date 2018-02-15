package cn.six.sup.nested_scroll.nested_scroll.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleViewPagerIndicator extends LinearLayout {

    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    private static final int COLOR_INDICATOR_COLOR = Color.GREEN;

    private String[] titles;
    private int tabCount;
    private int indicatorColor = COLOR_INDICATOR_COLOR;
    private float translationX;
    private Paint paint = new Paint();
    private int tabWidth;

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(indicatorColor);
        paint.setStrokeWidth(9.0F);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        tabWidth = w / tabCount;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
        tabCount = titles.length;
        generateTitleView();

    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(translationX, getHeight() - 2);
        canvas.drawLine(0, 0, tabWidth, 0, paint);
        canvas.restore();
    }

    public void scroll(int position, float offset) {
        /**
         * <pre>
         *  0-1:position=0 ;1-0:postion=0;
         * </pre>
         */
        translationX = getWidth() / tabCount * (position + offset);
        invalidate();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void generateTitleView() {
        if (getChildCount() > 0)
            this.removeAllViews();
        int count = titles.length;

        setWeightSum(count);
        for (int i = 0; i < count; i++) {
            TextView tv = new TextView(getContext());
            LayoutParams lp = new LayoutParams(0,
                    LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(COLOR_TEXT_NORMAL);
            tv.setText(titles[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setLayoutParams(lp);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            addView(tv);
        }
    }

}