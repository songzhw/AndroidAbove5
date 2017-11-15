package cn.six.sup.g_component.livedata;

import android.arch.lifecycle.MutableLiveData;

public class StaticDataContainer {
    public static MutableLiveData<Worker> worker = new MutableLiveData<>();
}
