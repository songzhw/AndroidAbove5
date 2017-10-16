package cn.song.and5.appusage;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.song.and5.R;

import java.util.List;


public class AppUsageDemo extends Activity {

    private UsageStatsManager mgr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        // requires minSDK is 22
        mgr = (UsageStatsManager)getSystemService(USAGE_STATS_SERVICE);

    }

    public void onClickSimpleButton(View v) {
        List<UsageStats> data = getUsageStats();
        for (UsageStats item : data) {
            System.out.println("szw ["+item.getPackageName()+"]");
        }
    }


    public void onClickSimpleButton2(View v) {

    }

    private List<UsageStats> getUsageStats() {
        long now = System.currentTimeMillis();
        long sixHoursAgo = now - 6 * 60 * 60 * 1000;
        List<UsageStats> ret = mgr.queryUsageStats(UsageStatsManager.INTERVAL_BEST, sixHoursAgo, now);
        return ret;
    }

}
