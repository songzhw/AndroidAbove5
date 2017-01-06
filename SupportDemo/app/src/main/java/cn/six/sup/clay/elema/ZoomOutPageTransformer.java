package cn.six.sup.clay.elema;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by songzhw on 2017-01-05
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.9f;
    private static final float MIN_ALPHA = 0.5f;

    private static float defaultScale = 0.9f;


    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();



//        if (position < -1) {
//            // the left of the first page should be empty
//            page.setAlpha(0);
//            page.setScaleX(defaultScale);
//            page.setScaleY(defaultScale);
//        } else if(position <= 1){
//
//        }
    }
}
