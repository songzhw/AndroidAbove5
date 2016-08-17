package cn.six.sup.design_lib;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.six.sup.R;

/**
 * Created by songzhw on 2016/2/21.
 */

// FloatingActionButton正常显示的情况下有个填充的颜色
// 点击的时候会有一个rippleColor，并且阴影的范围可以增大

// colorAccent 对应EditText编辑时、RadioButton选中、CheckBox等选中时的颜色
// rippleColor默认取的是theme中的colorControlHighlight。

// app:backgroundTint="#ff87ffeb"
// app:rippleColor="#33728dff"


public class FloatingActionButtonDemo extends AppCompatActivity {
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_demo);

        fab = (FloatingActionButton) findViewById(R.id.fab_demo_fab);

        // 只有写上了setOnClickListener， 才会有点击的变色！(app: rippleColor)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("szw onClick() fab");

//                Snackbar.make(fab, "Click FAB", Snackbar.LENGTH_SHORT).show();

                Snackbar.make(fab, "Click FAB", Snackbar.LENGTH_SHORT)
                        .setAction("Revert", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(FloatingActionButtonDemo.this, "Revert", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
