package cn.six.sup.rv.custom_layout_mgr.hex.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.six.sup.R;
import cn.six.sup.rv.custom_layout_mgr.hex.HexItemView;


public class HexViewDemo extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex_view_demo);

        HexItemView view = (HexItemView) findViewById(R.id.hexviewDemo);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("szw click");
    }
}
