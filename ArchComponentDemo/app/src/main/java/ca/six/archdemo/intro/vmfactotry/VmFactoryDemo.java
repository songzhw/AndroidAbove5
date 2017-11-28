package ca.six.archdemo.intro.vmfactotry;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ca.six.archdemo.intro.trap1.DupliViewModel;


public class VmFactoryDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DemoVmFactory factory = new DemoVmFactory();
        DemoVm vm = ViewModelProviders.of(this, factory).get(DemoVm.class);

//        DupliViewModel vm2 = ViewModelProviders.of(this, factory).get(DupliViewModel.class);
    }
}
