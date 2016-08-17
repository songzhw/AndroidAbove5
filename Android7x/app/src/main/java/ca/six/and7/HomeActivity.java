package ca.six.and7;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class HomeActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // ===============     Lambda      ===============
        Button btn = new Button(this);
        btn.setOnClickListener( (view)-> System.out.println("szw"));

        // Warning: Call requires API Level 24 (current min is 16) : java.util.Collection # stream
        List<String> list = new ArrayList(Arrays.asList("one","two","3"));
        list.stream().map( (it) -> "["+it+"]").forEach( (it) -> System.out.println(it));


        // =============== Method Reference ===============
        handler = new Handler(Looper.getMainLooper());
        runnable = () -> this.foo() ;
        handler.postDelayed(runnable, 1000);


        handler.postDelayed(this::foo, 3000);


        test(this::doRealJob, 23);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        handler.removeCallbacks( this::foo );

        super.onDestroy();
    }

    private void foo(){
        System.out.println("runnable");
    }

    // Supplier, Consumer, BiConsumer, Predicate, Function, BiFunction, BinaryOperator
    public void test(Consumer<Integer> fun, int arg){
        fun.accept(arg); //=> Requrie API 24.  (current min is 16)
    }

    public void doRealJob(int i){
        System.out.println("doRealJob "+i);
    }

}
