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

    //绘制阴影状态变量
    private float shadowRadius, shadowDx, shadowDy;
    private int shadowColor;

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
        //关闭硬件加速，为了可以设置阴影
        setLayerType(LAYER_TYPE_SOFTWARE, null);

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
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isEventInPath(event)) {
                return false;
            }
        }
        return super.dispatchTouchEvent(event);
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

    /**
     * 设置多边形边框颜色
     */
    public void setOuterColor(int color) {
        outerColor = color;
        outerPaint.setColor(color);
        invalidate();
    }

    public int getOuterColor() {
        return outerColor;
    }

    /**
     * 设置正六边形的内部填充颜色
     */
    public void setInnerColor(int color) {
        innerColor = color;
        innerPaint.setColor(color);
        invalidate();
    }

    public int getInnerColor() {
        return innerColor;
    }

    /**
     * 设置正六边形的边框大小
     */
    public void setOuterWidth(int outerWidth) {
        if (outerWidth < 0) {
            return;
        }
        this.outerWidth = outerWidth;
        outerPaint.setStrokeWidth(outerWidth);
        invalidate();
    }

    public int getOutWidth() {
        return outerWidth;
    }

    /**
     * 设置正六边形是否被填充(无边框)
     */
    public void setViewFullMode(boolean isFull) {
        this.isHasStroke = isFull;
        invalidate();
    }

    public boolean getViewFullMode() {
        return isHasStroke;
    }

    /**
     * 设置正六边形的外圆半径
     */
    public void setRadius(int radius) {
        this.radius = radius;
        setHexPath(POLYGON_COUNT);
        invalidate();
    }

    public int getRadius() {
        return radius;
    }

    /**
     * 设置阴影  指的是实心的阴影
     */
    public void setShadowLayer(float radius, float dx, float dy, int color, boolean isOuter) {
        if (isOuter && isHasStroke) {
            outerPaint.setShadowLayer(radius, dx, dy, color);
        } else if (!isOuter) {
            innerPaint.setShadowLayer(radius, dx, dy, color);
        } else {
            return;
        }
        shadowRadius = radius;
        shadowDx = dx;
        shadowDy = dy;
        shadowColor = color;
        invalidate();
    }

    public float getShadowRadius() {
        return shadowRadius;
    }

    public float getShadowDx() {
        return shadowDx;
    }

    public float getShadowDy() {
        return shadowDy;
    }

    @ColorInt
    public int getShadowColor() {
        return shadowColor;
    }

}