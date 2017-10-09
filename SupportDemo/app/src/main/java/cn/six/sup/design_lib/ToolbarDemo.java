package cn.six.sup.design_lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016/2/19
 */
public class ToolbarDemo extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_demo_toolbar);
        toolbar.setLogo(R.drawable.ic_launcher);
        toolbar.setTitle("Toolbar Demo");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setSubtitleTextColor(0xffcccccc);
        toolbar.setSubtitle("A starter of the design library");

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow); // setNavigationIcon需要放在 setSupportActionBar之后才会生效。

        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarDemo.this, "click navi", Toast.LENGTH_SHORT).show();
            }
        });
        // setNavigationOnClickListener需要放在 setSupportActionBar之后才会生效。
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_demo, menu);
        return true;
    }

    // return "ture",  if the event was handled
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String msg = "";
        switch (menuItem.getItemId()) {
            case R.id.menu_toolbardemo_edit:
                msg += "Click edit";
                break;
            case R.id.menu_toolbardemo_share:
                msg += "Click share";
                break;
            case R.id.menu_toolbardemo_settings:
                msg += "Click setting";
                break;
        }

        if (!msg.equals("")) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
