package cn.six.sup.design_lib;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016/2/22
 */
public class NavigationViewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navview_demo);

        NavigationView nav = (NavigationView) findViewById(R.id.navigation);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                System.out.println("szw item = " + item.getTitle());
                // drawerLayout.closeDrawers()
                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.dtb_navview);
        toolbar.setLogo(R.drawable.ic_launcher);
        toolbar.setTitle("Toolbar Demo");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setSubtitle("A starter of the design library");
        toolbar.setSubtitleTextColor(0xffcccccc);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.dtl_navview);

        // One way to add tabs : ViewPager
//        tabLayout.setupWithViewPager(vp);
//        tabLayout.setTabsFromPagerAdapter(vpAdapter);

        // another way to add tabs:
        tabLayout.addTab(tabLayout.newTab().setText("One"));
        tabLayout.addTab(tabLayout.newTab().setText("Two"));
        tabLayout.addTab(tabLayout.newTab().setText("Three"));
    }
}

