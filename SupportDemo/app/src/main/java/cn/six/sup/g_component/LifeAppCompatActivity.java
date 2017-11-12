package cn.six.sup.g_component;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class LifeAppCompatActivity extends AppCompatActivity implements LifecycleRegistryOwner {
    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRegistry.markState(Lifecycle.State.STARTED);
    }


    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}
