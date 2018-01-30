package ca.six.archdemo.how.lifecycle;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyReportFragment extends Fragment {
    // 对于同一个Activity, 可能会有多个lifecycle的observer
    List<MyLifecycleListener> listeners = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void dispatch() {
        for(MyLifecycleListener listener : listeners){

        }
    }
}
