package ca.six.archdemo.intro.transfer.mediator;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

public class MediatorDemo {
    LiveData<String> liveData1 = new MutableLiveData<>();
    LiveData<String> liveData2 = new MutableLiveData<>();

    /*
    every time onChanged callback is called for either of them,
    we set a new value in liveDataMerger.
     */
    public void first() {
        MediatorLiveData<String> liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(liveData1, value -> liveDataMerger.setValue(value));
        liveDataMerger.addSource(liveData2, value -> liveDataMerger.setValue(value));
    }

    /*
    we only want 10 values emitted by liveData1, to be merged in the liveDataMerger.
    Then, after 10 values, we can stop listening to liveData1 and remove it as a source.
     */
    public void second() {
        MediatorLiveData<String> liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(liveData1, new Observer<String>() {
            private int count = 1;

            @Override
            public void onChanged(@Nullable String s) {
                count++;
                liveDataMerger.setValue(s);
                if (count > 10) {
                    liveDataMerger.removeSource(liveData1);
                }
            }
        });
    }
}
