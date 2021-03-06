package cn.six.sup.other.spring;

import android.app.Activity;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import cn.six.sup.R;

public class SpringActivity03 extends Activity {

    float STIFFNESS = SpringForce.STIFFNESS_MEDIUM;//硬度
    float DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;//阻尼
    SpringAnimation xAnimation;//x方向
    SpringAnimation yAnimation;//y方向
    View movingView;//图片
    float dX = 0f;
    float dY = 0f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_three);

        movingView = findViewById(R.id.movingView);

        xAnimation = createSpringAnimation(movingView, SpringAnimation.TRANSLATION_X, 0);
        yAnimation = createSpringAnimation(movingView, SpringAnimation.TRANSLATION_Y, 0);

        movingView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        // 计算到左上角的距离
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();

                        // 取消动画以便按住图片
                        xAnimation.cancel();
                        yAnimation.cancel();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //  另一种改变View的LayoutParams（位置）的方式
                        movingView.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    case MotionEvent.ACTION_UP:
                        xAnimation.start();
                        yAnimation.start();
                        break;
                }
                return true;
            }
        });

    }

    SpringAnimation createSpringAnimation(View view, DynamicAnimation.ViewProperty property, float finalPosition) {
        //创建弹性动画类SpringAnimation
        SpringAnimation animation = new SpringAnimation(view, property);
        //SpringForce类，定义弹性特质
        SpringForce spring = new SpringForce(finalPosition);
        spring.setStiffness(STIFFNESS);
        spring.setDampingRatio(DAMPING_RATIO);
        //关联弹性特质
        animation.setSpring(spring);
        return animation;
    }

}

/*
        // 以图片的初始位置创建动画对象
        movingView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                xAnimation = createSpringAnimation(
                        movingView, SpringAnimation.X, movingView.getX(), STIFFNESS, DAMPING_RATIO);
                yAnimation = createSpringAnimation(
                        movingView, SpringAnimation.Y, movingView.getY(), STIFFNESS, DAMPING_RATIO);
                //初始位置确定，移除监听
                movingView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
*/


/*
    SpringAnimation createSpringAnimation(View view,
                                               DynamicAnimation.ViewProperty property,
                                               Float finalPosition,
                                               @FloatRange(from = 0.0) Float stiffness,
                                               @FloatRange(from = 0.0) Float dampingRatio) {
        //创建弹性动画类SpringAnimation
        SpringAnimation animation = new SpringAnimation(view, property);
        //SpringForce类，定义弹性特质
        SpringForce spring = new SpringForce(finalPosition);
        spring.setStiffness(stiffness);
        spring.setDampingRatio(dampingRatio);
        //关联弹性特质
        animation.setSpring(spring);
        return animation;
    }
*/
