
package ca.six.archdemo.intro.transfer.auto;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ca.six.archdemo.R;


public class TransferDemo2 extends AppCompatActivity {
    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_tv);

        tv1 = findViewById(R.id.tvOne);
        tv2 = findViewById(R.id.tvTwo);
        tv3 = findViewById(R.id.tvThree);

        TransferViewModel2 vm = ViewModelProviders.of(this).get(TransferViewModel2.class);
        vm.onCreate();

        vm.getData().observe(this, names -> vm.onNamesFetched());

        vm.getUser().observe(this, user -> {
            tv2.setText(user.toString());
        });

        vm.getFirst().observe(this, tv1::setText);

        System.out.println("szw onCreate() finished()");
    }
}

