package cn.six.sup.rv.dragdrop.groups;


import cn.six.sup.R;

public class Company {
    public static final int Country_US = 0;
    public static final int Country_CHINA = 1;

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_CONTENT = 1;

    public String name;
    public int iconRes;
    public int country;
    public int type;

    public Company(String name, int iconRes, int country) {
        this.name = name;
        this.iconRes = iconRes;
        this.country = country;
        type = TYPE_CONTENT;

    }

    public Company(String name, int country) {
        this.name = name;
        this.iconRes = R.drawable.ic_launcher;
        this.country = country;
        type = TYPE_CONTENT;
    }

    public Company(String name) {
        this.name = name;
        this.type = TYPE_TITLE;
    }
}
