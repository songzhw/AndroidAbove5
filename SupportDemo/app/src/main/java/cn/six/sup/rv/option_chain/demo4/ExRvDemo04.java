package cn.six.sup.rv.option_chain.demo4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.option_chain.demo2.NumbersAdapter;


public class ExRvDemo04 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_rv_four);

        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            numbers.add("item " + i);
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvEx04);
        NumbersAdapter adapter = new NumbersAdapter(this, numbers);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        // fix bug : Rv inside Nsv cannot fling
        // http://stackoverflow.com/questions/32175603/fling-not-working-for-nested-scrollview
        // RV也是NestedScrollingChild的实现类。 此方法其实就是调用NestedScrollingChildHelper的方法
        rv.setNestedScrollingEnabled(false);

        final NestedScrollView nsv = (NestedScrollView) findViewById(R.id.nsv_ex_rv_four);
        // 处理ScrollView的焦点问题
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //直接用ptrScrollView.scrollTo(0,0)起不到作用！
                nsv.scrollTo(0, 0);
            }
        }, 100);

    }

}