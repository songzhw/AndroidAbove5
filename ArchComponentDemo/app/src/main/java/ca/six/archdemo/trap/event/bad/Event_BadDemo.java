package ca.six.archdemo.trap.event.bad;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ca.six.archdemo.R;

public class Event_BadDemo extends AppCompatActivity {
    private TextView tv;
    private Event_BadDemo self;
    private Event_BadViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        self = this;
        tv = findViewById(R.id.tv_simple);

        vm = ViewModelProviders.of(this).get(Event_BadViewModel.class);

        vm.getMessage().observe(this, s -> {
            System.out.println("szw updated ~");
            Toast.makeText(self, "updated " + s, Toast.LENGTH_SHORT).show();
        });

        vm.getClickCount().observe(this, count -> { // count : Integer
                    System.out.println("szw click(2) : " + count);
                    Toast.makeText(self, "szw click : " + count, Toast.LENGTH_SHORT).show();
                }
        );
    }

    public void onClickSimpleButton(View v) {
        vm.fetchMessage();
    }

    public void onClickSimpleButton2(View v) {
        vm.clickAndIncrese();
    }
}
