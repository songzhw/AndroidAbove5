package cn.six.sup.rv.custom_layout_mgr.hex;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.six.sup.R;

public class HexItemView extends View {
    private static final int POLYGON_COUNT = 6; // 正六边形的边数量
    private static final int DEFAULT_OUTER_WIDTH = 4; // 默认的边框宽度
    public static final int DEFAULT_OUTER_COLOR = Color.parseColor("#f5c421"); // 默认的边框颜色
    private int maxRadius, radius;//包裹着正六边形的圆的半径
    private int outerWidth; //边框线的宽度
    private int outerColor, innerColor;// 内侧颜色和外侧的颜色
    private int centerX, centerY;

    private Paint outerPaint, innerPaint;
    private Path viewPath;
    private Region region;
    private boolean isHasStroke; //是否无边框填充

    public HexItemView(Context context) {
        this(context, null);
    }

    public HexItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HexItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypeArray = context.obtainStyledAttributes(attrs, R.styleable.Polygon, defStyleAttr, 0);
        radius = mTypeArray.getDimensionPixelSize(R.styleable.Polygon_radius, 0);
        innerColor = mTypeArray.getColor(R.styleable.Polygon_innerColor, Color.WHITE);
        outerColor = mTypeArray.getColor(R.styleable.Polygon_outerColor, DEFAULT_OUTER_COLOR);
        outerWidth = mTypeArray.getDimensionPixelSize(R.styleable.Polygon_outerWidth, DEFAULT_OUTER_WIDTH);
        isHasStroke = mTypeArray.getBoolean(R.styleable.Polygon_isHasStroke, true);
        mTypeArray.recycle();
        initData();
    }

    private void initData() {
        outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(outerWidth);
        outerPaint.setColor(outerColor);

        innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        innerPaint.setColor(innerColor);

        region = new Region();
        viewPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        maxRadius = Math.min(centerX, centerY);
        if (radius <= 0 || radius > maxRadius) {
            radius = maxRadius;
        }
        setHexPath(POLYGON_COUNT);
    }

    public void setHexPath(int count) {
        if (count < POLYGON_COUNT) {
            return;
        }
        viewPath.reset();
        for (int i = 0; i < count; i++) {
            //当前角度
            int angle = 360 / count * i;
            if (i == 0) {
                viewPath.moveTo(centerX + radius * MathUtil.cos(angle), centerY + radius * MathUtil.sin(angle));
            } else {
                viewPath.lineTo(centerX + radius * MathUtil.cos(angle), centerY + radius * MathUtil.sin(angle));
            }
        }
        viewPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(viewPath, innerPaint);
        if (isHasStroke) {
            canvas.drawPath(viewPath, outerPaint);
        }

        // [for debug]: to get the correct circle that is outside of hexView
//        canvas.drawCircle(centerX, centerY, radius, outerPaint);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isEventInPath(event)) {
                return false;
            }
        }

        boolean ret = super.dispatchTouchEvent(event);
        return ret;
    }

    private boolean isEventInPath(MotionEvent event) {
        RectF bounds = new RectF();
        //计算Path的边界
        viewPath.computeBounds(bounds, true);
        //将边界赋予Region中
        region.setPath(viewPath, new Region((int) bounds.left, (int) bounds.top,
                (int) bounds.right, (int) bounds.bottom));
        //判断 当前的触摸点是否在这个范围内
        return region.contains((int) event.getX(), (int) event.getY());
    }

}