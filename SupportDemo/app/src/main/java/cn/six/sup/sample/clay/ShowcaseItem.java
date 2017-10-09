package cn.six.sup.sample.clay;

import android.support.annotation.DrawableRes;

public class ShowcaseItem {
    @DrawableRes
    public int icon;
    public String text;

    public ShowcaseItem(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }
}