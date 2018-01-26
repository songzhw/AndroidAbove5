package ca.six.archdemo.intro.transfer;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class TransformationDemo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransformationDemoViewMode vm = ViewModelProviders.of(this).get(TransformationDemoViewMode.class);

        vm.foo();
    }
}
